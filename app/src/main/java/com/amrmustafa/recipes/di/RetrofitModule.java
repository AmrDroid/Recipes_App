package com.amrmustafa.recipes.di;

import com.amrmustafa.recipes.network.RecipeApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public static RecipeApiService provideRecipeApiService(){
        return new Retrofit.Builder()
                .baseUrl("https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RecipeApiService.class);

    }
}
