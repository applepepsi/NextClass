package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun TermsAndConditionsTextComponent() {

    val all="모든 "
    val terms="이용 약관"
    val agree= "에 동의합니다."



    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 15.sp)) {
            append(all)
            withStyle(style = SpanStyle(color = Color.Red)) { // 특정 부분 스타일 설정
                pushStringAnnotation(tag = terms, annotation = terms)
                append(terms)
            }
            append(agree)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("test", span.toString())
            }
    })

}


@Composable
fun FindIdOrPasswordTextComponent() {

    val id="아이디 "
    val or="또는 "
    val password="비밀번호"
    val forget= "를 잊어버렸나요?"


    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 13.sp)) {
            withStyle(style = SpanStyle(color = Color.Red)) { // 특정 부분 스타일 설정
                pushStringAnnotation(tag = id, annotation = id)
                append(id)
            }
            append(or)
            withStyle(style = SpanStyle(color = Color.Red)) { // 특정 부분 스타일 설정
                pushStringAnnotation(tag = password, annotation = password)
                append(password)
            }
            append(forget)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("test", span.toString())
            }
    })

}

