package com.example.newsfeedusingcompose.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScaffold(
    shouldShowTopBar: Boolean = false,
    showNavigationBack: Boolean = false,
    setNavigationIcon: ImageVector = Icons.Filled.Menu,
    screenName: String,
    onNavActionButtonClick: (() -> Unit)? = null,
    composableView: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            if (shouldShowTopBar) TopBarView(
                showNavigationBack,
                setNavigationIcon,
                onNavActionButtonClick,
                screenName
            )
        },
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        }) {
        Column {
            Spacer(modifier = Modifier.height(it.calculateTopPadding()))
            composableView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarView(
    shouldShowNavigationBack: Boolean = false,
    appBarNavIcon: ImageVector = Icons.Filled.Menu,
    onNavActionButtonClick: (() -> Unit)? = null,
    @PreviewParameter(ViewProvider::class) screenName: String
) {
    val dark = isSystemInDarkTheme()
    /*Here Box is acting as a stack view , items organized as one top of another */
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = if (dark) Color.DarkGray else Color.White), contentAlignment = Center
    ) {
        if (shouldShowNavigationBack) {
            IconButton(
                onClick = {
                    if (onNavActionButtonClick != null) {
                        onNavActionButtonClick()
                    }
                },
                modifier = Modifier.align(CenterStart)
            ) {
                Icon(
                    imageVector = appBarNavIcon,
                    contentDescription = appBarNavIcon.toString()
                )
            }
        }

        /*Box helps with vertical alignment but to organize horizontally need to use the align property of inner widgets*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = screenName, modifier = Modifier.align(alignment = Center))
        }
    }
}

