package com.pine.prexuretest.beans

data class Store(
  val address: String,
  val distance: Int,
  val featureList: List<String>,
  val id: Int,
  val latitude: Double,
  val longitude: Double,
  val name: String

)