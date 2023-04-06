package com.hakemy.marketsamer.utils.services

import com.hakemy.marketsamer.ui.addAddress.models.GovernmentItem
import com.hakemy.marketsamer.ui.cart.CartRequestAdd
import com.hakemy.marketsamer.ui.cart.models.CartResponse
import com.hakemy.marketsamer.ui.chooseAddresse.response.AddressItem
import com.hakemy.marketsamer.ui.favList.IsFavouriteResponse
import com.hakemy.marketsamer.ui.home.entities.response.MainScreenResponse
import com.hakemy.marketsamer.ui.offers.entities.response.OffersResponse
import com.hakemy.marketsamer.ui.offers.entities.response.Products
import com.hakemy.marketsamer.ui.onboarding.servicesModels.OnboardingModel
import com.hakemy.marketsamer.ui.profile.editePersonalData.UserDataResponse
import com.hakemy.marketsamer.ui.profile.notification.model.NotificationsResponse
import com.hakemy.marketsamer.ui.register.serviceModel.CreateNewAccountRequest
import com.hakemy.marketsamer.ui.register.serviceModel.LoginRequest
import com.hakemy.marketsamer.ui.register.serviceModel.ResetPasswordRequest
import com.hakemy.marketsamer.ui.register.serviceModel.VerificationPhoneRequest
import com.hakemy.marketsamer.ui.register.serviceModel.response.createAccount.CreateNewAccountResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.login.LoginResponse
import com.hakemy.marketsamer.ui.register.serviceModel.response.resetPassword.ResetPasswordResponse
import com.hakemy.marketsamer.ui.showProduct.entities.ProductDetailsResponse
import retrofit2.http.*

const val ADDRESS_ID = "address_id"

interface ApiService {


    @GET("landing_board")
    suspend fun onBoarding():OnboardingModel

    @POST("register")
    suspend fun register(@Body createNewAccountRequest: CreateNewAccountRequest): CreateNewAccountResponse

    @POST("verificationPhone")
    suspend fun verificationPhone(@Body verificationCode: VerificationPhoneRequest):com.hakemy.marketsamer.ui.register.serviceModel.response.verificationCode.VerificationCode

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("forgetPassword")
    suspend fun forgetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    @GET("offers")
    suspend fun getOffers(): OffersResponse

    @GET("productFromCategory/{id}")
    suspend fun productFromCategory(@Path("id") id: String): OffersResponse



    @GET("mainPage")
    suspend fun mainPage(): MainScreenResponse



    @GET("product/{productId}")
    suspend fun productDetails(@Path("productId") id: String): ProductDetailsResponse
    @GET("search")
    suspend fun search(@Query("search") search: String): BaseResponse<Products>

    @FormUrlEncoded
    @POST("isFavourite")
    suspend fun isFavorite(
        @Field("id") id: String,
    ): BaseResponse<IsFavouriteResponse>

    @GET("getAddress")
    suspend fun savedAddresses(): BaseResponse<MutableList<AddressItem>>

    @POST("add/cart")
    suspend fun addToCart(@Body addToCartRequest: CartRequestAdd): BaseResponse<Any?>


    @POST("get/cart")
    suspend fun getCart(
       @Body macAddress: HashMap<String,String>,
    ): CartResponse

    @FormUrlEncoded
    @POST("edit/cart")
    suspend fun editCart(
        @Field("product_order_id") id: String,
        @Field("type") type: String
    ): BaseResponse<Any?>

    @FormUrlEncoded
    @POST("delete/cart")
    suspend fun deleteCart(
        @Field("order_id") orderId: String,
        @Field("product_order_id") id: String
    ): BaseResponse<Any?>

    @GET(GOVERNMENTS)
    suspend fun governments(): BaseResponse<MutableList<GovernmentItem>>
    @FormUrlEncoded
    @POST(ADD_NEW_ADDRESS)
    suspend fun addAddress(
        @Field("lat") lat: String,
        @Field("long") lng: String,
        @Field("type") type: String,
        @Field("addresss") address: String,
        @Field("governments_id") governmentsId: String,
        @Field("region_id") regionId: String,
        @Field("piece_number") pieceNumber: String,
        @Field("Street_number") StreetNumber: String,
        @Field("house_number") houseNumber: String,
        @Field("floor_number") floorNumber: String,
        @Field("Apartment_number") ApartmentNumber: String?,
        @Field("information") information: String?,
        @Field("phone") phone: String
    ): BaseResponse<AddressItem>
    @FormUrlEncoded
    @POST(CHOOSE_ADDRESS)
    suspend fun chooseAddress(
        @Field("id") id: String,
    ): BaseResponse<Any?>
    @POST(DELETE_ADDRESS)
    suspend fun deleteAddress(@Query("id") id: Int): BaseResponse<Any?>


    @FormUrlEncoded
    @POST("$UPDATE_ADDRESS/{$ADDRESS_ID}")
    suspend fun updateAddress(
        @Path(ADDRESS_ID) addressId: String,
        @Field("lat") lat: String,
        @Field("long") lng: String,
        @Field("type") type: String,
        @Field("addresss") address: String,
        @Field("governments_id") governmentsId: String,
        @Field("region_id") regionId: String,
        @Field("piece_number") pieceNumber: String,
        @Field("Street_number") StreetNumber: String,
        @Field("house_number") houseNumber: String?,
        @Field("floor_number") floorNumber: String?,
        @Field("Apartment_number") ApartmentNumber: String?,
        @Field("information") information: String?,
        @Field("phone") phone: String
    ): BaseResponse<AddressItem>

    @GET(NOTIFICATIONS)
    suspend fun notifications(): BaseResponse<NotificationsResponse>
    @GET(FAVOURITE_PAGE)
    suspend fun favouritesPage(): BaseResponse<Products>

    @FormUrlEncoded
    @POST(UPDATE_PROFILE)
    suspend fun updateProfile(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("_method") method: String = "put"
    ): BaseResponse<UserDataResponse>
}