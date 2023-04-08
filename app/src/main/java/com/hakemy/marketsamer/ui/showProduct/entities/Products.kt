package com.hakemy.marketsamer.ui.showProduct.entities


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Products(
    @SerializedName("content")
    val content: String?, // يتم استخدام غلاف الإسفنج لإنشاء تأثير فريد ومختلف وجميل على أي سطح، إذا كنت تشعر بالملل من المظهر العادي لطلاء الجدار وتحتاج إلى شكل جديد على الجدران، فيمكنك استخدام هذا الإسفنج لإعطائك تأثير المحبب. يتم استخدام غلاف الإسفنج لإنشاء تأثير فريد ومختلف وجميل على أي سطح، إذا كنت تشعر بالملل من المظهر العادي لطلاء الجدار وتحتاج إلى شكل جديد على الجدران، فيمكنك استخدام هذا الإسفنج لإعطائك تأثير المحبب. يتم استخدام غلاف الإسفنج لإنشاء تأثير فريد ومختلف وجميل على أي سطح، إذا كنت تشعر بالملل من المظهر العادي لطلاء الجدار وتحتاج إلى شكل جديد على الجدران، فيمكنك استخدام هذا الإسفنج لإعطائك تأثير المحبب. يتم استخدام غلاف الإسفنج لإنشاء تأثير فريد ومختلف وجميل على أي سطح، إذا كنت تشعر بالملل من المظهر العادي لطلاء الجدار وتحتاج إلى شكل جديد على الجدران، فيمكنك استخدام هذا الإسفنج لإعطائك تأثير المحبب. يتم استخدام غلاف الإسفنج لإنشاء تأثير فريد ومختلف وجميل على أي سطح، إذا كنت تشعر بالملل من المظهر العادي لطلاء الجدار وتحتاج إلى شكل جديد على الجدران، فيمكنك استخدام هذا الإسفنج لإعطائك تأثير المحبب.
    @SerializedName("discount")
    val discount: String?, // 2.000
    @SerializedName("feature")
    val feature: String?, // عربي
    @SerializedName("id")
    val id: Int, // 44
    @SerializedName("image_path")
    val imagePath: String?, // https://sharwa.masharia.co/storage/images/mainProduct/kAkOaSPL1EP4vlaUFqdyDLGqXi7YgPZ1O9J35UBW.jpg
    @SerializedName("images")
    val images: java.util.ArrayList<Image>?,
    @SerializedName("is_favourite")
    var isFavourite: Boolean, // false
    @SerializedName("mainprice")
    val mainprice: String?, // 5.000
    @SerializedName("minorder")
    val minorder: Int, // 2
    @SerializedName("name")
    val name: String?, // دهانات جوتن
    @SerializedName("options")
    val options: java.util.ArrayList<Option>?,
    @SerializedName("prefitPrice")
    val prefitPrice: String?, // 60
    @SerializedName("shippingCostFreeAfter")
    val shippingCostFreeAfter: String?, // 5
    @SerializedName("special")
    val special: ArrayList<Special>,
    @SerializedName("tager_id")

    var tagerId: Int? = 0
)