package com.alex.findjob.screens.common

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

// TODO: clear
sealed interface BottomSheetNavigateSideEffect : BaseSideEffect {
    object Hide : BottomSheetNavigateSideEffect
    class PopWithCollapsing(
        val ignoreHalfExpandedState: Boolean = false
    ) : BottomSheetNavigateSideEffect
    class PopUntilWithCollapsing(
        val untilExpression: (Screen) -> Boolean,
        val ignoreHalfExpandedState: Boolean = false
    ) : BottomSheetNavigateSideEffect
    class Show(val screen: Screen) : BottomSheetNavigateSideEffect
    class ShowWithCollapsing(
        val screens: List<Screen>,
        val ignoreHalfExpandedState: Boolean = false
    ) : BottomSheetNavigateSideEffect
    class PushWithCollapsing(
        val screen: Screen,
        val ignoreHalfExpandedState: Boolean = true
    ) : BottomSheetNavigateSideEffect
}

fun BottomSheetNavigateSideEffect.isPopSideEffect(): Boolean {
    return this is BottomSheetNavigateSideEffect.Hide ||
            this is BottomSheetNavigateSideEffect.PopWithCollapsing ||
            this is BottomSheetNavigateSideEffect.PopUntilWithCollapsing
}

sealed interface ModalBottomSheetNavigateSideEffect : BaseSideEffect {
    object Hide : ModalBottomSheetNavigateSideEffect
    class Show(val screen: Screen) : ModalBottomSheetNavigateSideEffect
}

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
