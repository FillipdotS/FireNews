package com.fillipdots.firenews.ui.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fillipdots.firenews.R

@Composable
fun AccountDetail(
    isAccountAnonymous: Boolean,
    email: String?,
    accountId: String,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        if (isAccountAnonymous) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(R.string.not_signed_in), textAlign = TextAlign.Center)
            }

            Row {
                ElevatedButton(
                    modifier = Modifier.padding(start = 16.dp, end = 24.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 24.dp),
                    onClick = { onLoginClick() }
                ) {
                    Icon(
                        Icons.Filled.Login,
                        contentDescription = stringResource(R.string.login),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(stringResource(R.string.login))
                }
                ElevatedButton(
                    modifier = Modifier.padding(start = 16.dp, end = 24.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 24.dp),
                    onClick = { onSignUpClick() }
                ) {
                    Icon(
                        Icons.Filled.PersonAdd,
                        contentDescription = stringResource(R.string.sign_up),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(stringResource(R.string.sign_up))
                }
            }
        } else {
            Text("Signed in as: $email")
            Text("Account Id: \n$accountId")
            ElevatedButton(
                modifier = Modifier.padding(start = 16.dp, end = 24.dp),
                onClick = { onLogoutClick() }
            ) {
                Icon(
                    Icons.Filled.Logout,
                    contentDescription = stringResource(R.string.logout),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(stringResource(R.string.logout))
            }
        }
    }
}

@Preview(showBackground = true, name = "Anonymous Account")
@Composable
private fun AccountDetailPreview() {
    AccountDetail(true, null, "", { }, { }, {})
}

@Preview(showBackground = true, name = "Signed In")
@Composable
private fun AccountDetailPreviewSignedIn() {
    AccountDetail(false, "fillip@swansea.ac.uk", "123", { }, { }, {})
}