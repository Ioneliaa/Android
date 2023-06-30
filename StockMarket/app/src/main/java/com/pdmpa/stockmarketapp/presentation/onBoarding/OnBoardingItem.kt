package com.pdmpa.stockmarketapp.presentation.onBoarding

import com.pdmpa.stockmarketapp.R

class OnBoardingItem(
    val image: Int,
) {

    companion object {

        fun get(): List<OnBoardingItem> {
            return listOf(
                OnBoardingItem(
                    R.drawable.img_8
                ),
                OnBoardingItem(
                    R.drawable.img_9
                ),
                OnBoardingItem(
                    R.drawable.img_10
                ),
                OnBoardingItem(
                    R.drawable.img_11
                ),
                OnBoardingItem(
                    R.drawable.img_12
                ),
            )
        }

    }

}