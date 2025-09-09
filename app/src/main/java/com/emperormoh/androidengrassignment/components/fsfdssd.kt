package com.emperormoh.androidengrassignment.components

data class EvoucherCategory(
    val id: String? = null,
    val name: String? = null,
    val brand: EvoucherBrand? = null
)

data class EvoucherBrand(
    val brandName: String? = null,
    val discount: Double? = null,
    val vendors: List<EvoucherVendor>? = null
)

data class EvoucherVendor(
    val id: String? = null,
    val name: String? = null,
    val brandId: String? = null,
    val isPhoneNumberRequired: Boolean? = null,
    val isPrimaryOperator: Boolean? = null,
    val currency: String? = null,
    val vendorLogo: String? = null,
    val country: EvoucherCountry? = null
)

data class EvoucherCountry(
    val id: String? = null,
    val name: String? = null,
    val countryLogo: String? = null,
    val prefixes: List<String>? = null,
    val operatorPrefixes: List<String>? = null
)