# General settings for clear error reporting
-keepattributes SourceFile,LineNumberTable,Signature,*Annotation*

# Hilt / Dagger
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class **_HiltModules* { *; }
-keep class **_Factory { *; }
-keep class **_MembersInjector { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

-keep class denys.diomaxius.newzealandguide.data.model.** { *; }
-keepclassmembers class denys.diomaxius.newzealandguide.data.model.** {
    public <init>(...);
    public <fields>;
}