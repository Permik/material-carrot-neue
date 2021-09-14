package xyz.permik.carrotneue.ui.strings

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.processor.Strings
import cafe.adriel.lyricist.rememberStrings
import xyz.permik.carrotneue.Locales

@Strings(languageTag = Locales.EN, default = true)
val EnStrings = NeueStrings(
    helloWorld = "Hello, World!",
    appName = "Carrot Neue",
    addAccount = "Add account",
    moreVert = "More options"
)

class EnStringsProvider: PreviewParameterProvider<Lyricist<NeueStrings>>{
    override val values = sequenceOf(previewLyricist)
    override val count = values.count()
}

val previewStrings = mapOf(
    Locales.EN to EnStrings
)

val previewLyricist = Lyricist(Locales.EN, previewStrings)

