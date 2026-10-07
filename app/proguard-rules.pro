# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\rfinistrosa\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
#
# -dontoptimize estaba aquí antes: desactivaba la optimización de R8 aunque
# minifyEnabled estuviera a true, que es justo lo que Google Play pide mejorar
# (ofuscación/reducción de código DEX por debajo del 25%). Lo hemos quitado.

# Add any project specific keep options here:

# uCrop (pantalla de recortar foto): reglas recomendadas por la propia
# librería para que R8 no elimine ni rompa nada al reducir/ofuscar.
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }
# Reglas para resolver el error de LoudnessCodecController con AdMob
# Estas clases son parte del SDK de Android, pero si R8 las pierde,
# indicamos que las mantenga.
-keep class android.media.LoudnessCodecController$OnLoudnessCodecUpdateListener { *; }
-keep class android.media.LoudnessCodecController { *; }
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
