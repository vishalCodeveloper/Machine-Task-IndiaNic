object Versions{
    const val kotlin_version = "1.5.10"
    const val gradle_version = "4.1.1"
    const val coreKtx_version = "1.6.0"
    const val appcompat = "1.3.1"
    const val material_design_version = "1.4.0"
    const val constraint_layout_version = "2.1.0"
    const val navigation_ktx_version = "2.3.3"
    const val hilt_version = "2.38.1"
    const val hilt_viewmodel_version = "1.0.0-alpha03"
    const val multidex_version = "2.0.1"
    const val location = "17.0.0"
    const val lifeCycle = "2.4.0-alpha03"
    const val room = "2.3.0"
    const val gms_version = "4.3.10"
    const val firebase_version = "28.3.1"
    const val firebase_auth_version = "21.0.1"
    const val crypto_version = "1.0.0"
    const val crypto_credential_version = "1.0.0-alpha02"
    const val retrofit_version = "2.6.0"
    const val retrofit_okhttp_version = "4.5.0"
    const val otpView_version = "v1.1.2-ktx"
    const val jUnit_version = "4.+"
    const val extJUnit_version = "1.1.3"
    const val espresso_version = "3.4.0"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
}

object Design {
    const val materialDesign = "com.google.android.material:material:${Versions.material_design_version}"
}

object Android{
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    const val coreKtx =  "androidx.core:core-ktx:${Versions.coreKtx_version}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"
}

object Navigation {
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_ktx_version}"
    const val navigationUiKtx =  "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ktx_version}"
    const val navigationSafeArgs =  "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_ktx_version}"
}

object PlayService{
    const val location = "com.google.android.gms:play-services-location:${Versions.location}"
    const val maps = "com.google.android.gms:play-services-maps:${Versions.location}"
}

object Lifecycle {
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifecycleRunTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val lifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifeCycle}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifeCycle}"
}


object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object Hilt {
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    const val hiltAndroid =  "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hiltCompiler =  "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    const val hiltViewModel =  "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel_version}"
}

object Firebase {
    const val play_service = "com.google.gms:google-services:${Versions.gms_version}"
    const val core = "com.google.firebase:firebase-bom:${Versions.firebase_version}"
    const val firebaseAuth =  "com.google.firebase:firebase-auth-ktx:${Versions.firebase_auth_version}"
}

object Crypto {
    const val crypto = "androidx.security:security-crypto:${Versions.crypto_version}"
    const val cryptoCredential =  "androidx.security:security-identity-credential:${Versions.crypto_credential_version}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofitOkhttp3 =  "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_okhttp_version}"
}

object Other{
    const val otpView = "com.github.aabhasr1:OtpView:${Versions.otpView_version}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit_version}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
}