package ba.grbo.currencyconverter.util

object Constants {
    const val BACKGROUND_COLOR = "backgroundColor"
    const val TEXT_COLOR = "textColor"
    const val TYPEFACE = "typeface"
    const val BACKGROUND = "background"
    const val PLACEHOLDER = "placeholder"
    const val IMAGE_TINT_LIST = "imageTintList"
    const val ITEM_ICON_TINT_LIST = "itemIconTintList"
    const val ITEM_TEXT_COLOR = "itemTextColor"

    const val SCALE_DOWN_END = 0.35f

    // needs to be in sync (same value) with above if we want to have smooth interruptions
    const val SCALE_UP_START = 0.35f
    const val ANIM_TIME: Long = 150
    const val ANIM_TIME_DIFFERENTIATOR = 1f

    const val UNEXCHANGEABLE_CURRENCIES_TABLE = "unexchangeable_currencies_table"
    const val EXCHANGE_RATES_TABLE = "exchange_rates_table"
    const val MISCELLANEOUS_TABLE = "miscellaneous_table"
    const val CURRENCY_CONVERTER_DATABASE = "currency_converter_database"

    const val PARENT_COLUMNS = "code"
    const val CHILD_COLUMNS = "code"
    const val NAME = "nameResourceName"

    const val STRING = "string"
    const val DRAWABLE = "drawable"
}