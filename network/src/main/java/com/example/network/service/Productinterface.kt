package com.example.network.service

import com.example.data.model.auth_model.Signup
import com.example.data.model.auth_model.login.LoginUser
import com.example.data.model.auth_model.response.LoginResponse
import com.example.data.model.auth_model.response.SignupResponse
import com.example.data.model.cart_model.Carts
import com.example.data.model.cart_model.add_cart.AddCart
import com.example.data.model.cart_model.add_cart.response.AddCartResponse
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.single_product.SingleProduct
import com.example.data.model.update_user.UpdateUser
import com.example.data.model.update_user.response.UpdateUserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

const val SHOPPING_URL = "https://fakestoreapi.com/"

interface ProductInterface {

    @POST("users")
   suspend fun signUp(
        @Body user: Signup
   ): Response<SignupResponse>


    @POST("auth/login")
   suspend fun loginUser(
        @Body user: LoginUser
   ): Response<LoginResponse>


    @GET("products")
    suspend fun getallProducts(): Response<Products>


    @GET("products/{id}")
    suspend fun getSingleProduct(
        @Path("id") id : String
    ): Response<SingleProduct>

    @GET("carts")
    suspend fun getAddedCart(
    ): Response<Carts>


    @POST("carts")
    suspend fun addToCart(
        @Body addCart: AddCart
    ): Response<AddCartResponse>


    @PUT("users/7")
    suspend fun updateUser(
        @Body updateUser: UpdateUser
    ): Response<UpdateUserResponse>


}

