package com.alex.findjob.screens.common

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

sealed interface NavigateSideEffect : BaseSideEffect {
    class Push(val screen: Screen) : NavigateSideEffect
    class Replace(val screen: Screen) : NavigateSideEffect
    class ReplaceAll(val screen: Screen) : NavigateSideEffect
    object Pop : NavigateSideEffect
    class PopAndReplace(val screen: Screen) : NavigateSideEffect
    class PopUntil(val predicate: (Screen) -> Boolean): NavigateSideEffect
    object PopUntilRoot: NavigateSideEffect
}

fun pushNavigateEffect(screen: Screen) = NavigateSideEffect.Push(screen)
fun replaceNavigateEffect(screen: Screen) = NavigateSideEffect.Replace(screen)
fun replaceAllNavigateEffect(screen: Screen) = NavigateSideEffect.ReplaceAll(screen)
fun popNavigateEffect() = NavigateSideEffect.Pop
fun popAndReplaceNavigateEffect(screen: Screen) = NavigateSideEffect.PopAndReplace(screen)
fun popUntilNavigateEffect(predicate: (Screen) -> Boolean) = NavigateSideEffect.PopUntil(predicate)
fun popUntilRootNavigateEffect() = NavigateSideEffect.PopUntilRoot

fun Navigator.handleSideEffect(effect: NavigateSideEffect) {
    when(effect) {
        is NavigateSideEffect.Pop -> pop()
        is NavigateSideEffect.PopUntil -> popUntil(effect.predicate)
        is NavigateSideEffect.PopUntilRoot -> popUntilRoot()
        is NavigateSideEffect.Push -> push(effect.screen)
        is NavigateSideEffect.Replace -> replace(effect.screen)
        is NavigateSideEffect.PopAndReplace -> {
            pop()
            replace(effect.screen)
        }
        is NavigateSideEffect.ReplaceAll -> replaceAll(effect.screen)
    }
}
