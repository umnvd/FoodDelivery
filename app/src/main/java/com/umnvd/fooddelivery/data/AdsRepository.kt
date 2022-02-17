package com.umnvd.fooddelivery.data

import com.umnvd.fooddelivery.models.Ad

interface AdsRepository {

    fun getAds(): List<Ad>

}

class InMemoryAdsRepository : AdsRepository {

    private val ads = listOf(
        Ad(
            "https://s3-alpha-sig.figma.com/img/0e5f/8769/bbdcde65e7e95920e9f71a180817df1a?Expires=1646006400&Signature=BOHpRZDc2K9aME4NVRp9hEEQouXKMiycT0nRtmg1LndiFg-MPq9g7dHCLb3c-sAaTx5BacAcWJIsMNhhS0tXLVBmFbf1f6iR4a~dkRxNFC-~ZFMkgHI4blWWUmc6hW1Rf6Ng12EFtqwWuXLK8eNJXUkfelgBBLxSo0-oqo~mZ7gtpgYW3-LccvvNNt~qbFByzIBFRtefTZ8XPT2xjDJn5ED275QaYkOZM1riaBaNTbYEBGXCJY~elqLxMRNndttaxqCFubJfQrmEYVy0JMVoIU9o-2Kpr75Kj5vlGl1mSUOdbfNnAnzEWFcbSUj4r-bAqxcXapQSeMxiABSWkLmM8Q__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            "30% discount"
        ),
        Ad(
            "https://s3-alpha-sig.figma.com/img/b42b/b92c/f6acf7e8e259819d3dd44499cb49eb54?Expires=1646006400&Signature=EU77snIcxFqXmNPPhQMS2GLJ7xAvA-QHE3peKepm6IXYmI-XjePGPaNO2d7K9wRxO60b~8ONeg8l6iyBGKlHKwmTLdAsEnlnVb5mdUYL6W-TbNYnvyzptPypA4bzkWKkjnB~dCEda59Liai6nYwt2uMh32GMeNBnXIjs2NB21RPdLZLa5ExSt-fr89htzAMU~ykI56hKOsQXyqlwvzTSZ4CE16qoHl1GEI3UIHWrGfPm081c9LivqP7F3gdX51d5EamS~ghSxEcvAPH0EGORaB-cQ79ybq4njXNOkIvOnVWmUKN1eDdoODp7BNgm4zgpZ~szmRMJ-UWY5KvAkwl8Bg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            "Pizza for a birthday"
        ),
    )

    override fun getAds(): List<Ad> = ads

}