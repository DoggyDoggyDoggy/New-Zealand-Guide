# Basic settings for logs
-keepattributes SourceFile,LineNumberTable,Signature,*Annotation*,EnclosingMethod,InnerClasses

# Hilt / Dagger
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class **_HiltModules* { *; }
-keep class **_Factory { *; }
-keep class **_MembersInjector { *; }

# 3. Room и Converters
-keep class * extends androidx.room.RoomDatabase
-keep class denys.diomaxius.newzealandguide.data.local.room.converter.** { *; }
-dontwarn androidx.room.paging.**

# 4. GSON и Serialization
-keep class com.google.gson.** { *; }
-keepnames class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# STRONG MODEL PROTECTION (Firestore + Room)
# We preserve all classes with the @Keep annotation.
-keep @androidx.annotation.Keep class ** { *; }
-keepclassmembers @androidx.annotation.Keep class ** { *; }

# Force saving the ENTIRE package of models (both local and remote)
-keep class denys.diomaxius.newzealandguide.data.** { *; }
-keepnames class denys.diomaxius.newzealandguide.data.** { *; }

# Preserve constructors, fields, and methods across all models
-keepclassmembers class denys.diomaxius.newzealandguide.data.**.model.** {
    public <init>(...);
    public <init>();
    public <fields>;
    public <methods>;
}

# Specifically for Kotlin Data Classes (to avoid argument names getting lost)
-keepclasseswithmembers class denys.diomaxius.newzealandguide.data.**.model.** {
    public <init>(...);
}

# Firebase Firestore
-keep class com.google.firebase.firestore.** { *; }
-keepnames class com.google.firebase.firestore.** { *; }