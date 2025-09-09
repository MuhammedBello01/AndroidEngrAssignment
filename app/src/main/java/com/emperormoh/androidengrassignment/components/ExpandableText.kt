package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExpandableText(
    text: String,
    seeMoreText: String = " See more",
    seeLessText: String = " See less"
) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayText = remember(isExpanded, text) {
        if (!isExpanded && text.length > 50) {
            text.take(50).trimEnd() + "..."
        } else {
            text
        }
    }

    val toggleText = if (text.length > 50) {
        if (isExpanded) seeLessText else seeMoreText
    } else ""

    val annotatedText = buildAnnotatedString {
        append(displayText)
        if (toggleText.isNotEmpty()) {
            append(toggleText)
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline
                ),
                start = displayText.length,
                end = displayText.length + toggleText.length
            )
            addStringAnnotation(
                tag = "TOGGLE",
                annotation = "toggle",
                start = displayText.length,
                end = displayText.length + toggleText.length
            )
        }
    }

    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "TOGGLE", start = offset, end = offset)
                .firstOrNull()?.let {
                    isExpanded = !isExpanded
                }
        }
    )
}

@Composable
fun ExpandableText2(
    text: String,
    modifier: Modifier = Modifier,
    maxCharacterLength: Int = 50,
    seeMoreText: String = " See more",
    seeLessText: String = " See less"
) {
    var isExpanded by remember { mutableStateOf(false) }
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val toggleText = if (text.length > maxCharacterLength) {
        if (isExpanded) seeLessText else seeMoreText
    } else ""

    val displayText = remember(isExpanded, text) {
        if (!isExpanded && text.length > maxCharacterLength) {
            text.take(maxCharacterLength).trimEnd() + "..."
        } else {
            text
        }
    }

    val annotatedText = remember(displayText, toggleText) {
        buildAnnotatedString {
            append(displayText)
            if (toggleText.isNotEmpty()) {
                val start = length
                append(toggleText)
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = start,
                    end = start + toggleText.length
                )
                addStringAnnotation(
                    tag = "TOGGLE",
                    annotation = "toggle",
                    start = start,
                    end = start + toggleText.length
                )
            }
        }
    }

    Text(
        text = annotatedText,
        modifier = modifier
            .pointerInput(annotatedText) {
                detectTapGestures { position ->
                    layoutResult?.let { layout ->
                        val offset = layout.getOffsetForPosition(position)
                        annotatedText.getStringAnnotations("TOGGLE", offset, offset)
                            .firstOrNull()?.let {
                                isExpanded = !isExpanded
                            }
                    }
                }
            },
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        onTextLayout = { layoutResult = it }
    )
}






@Preview(showBackground = true)
@Composable
fun ExpandableTextPreview() {
    ExpandableText2(
        text = "This is a very long message that should show see more if it is more than 50 characters, " +
                "and collapse back when see less is clicked. I am doing some test to actually confirm this"
    )
}

