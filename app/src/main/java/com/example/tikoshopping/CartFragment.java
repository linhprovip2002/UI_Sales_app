package com.example.tikoshopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tikoshopping.API.APIService;
import com.example.tikoshopping.Service.Login;
import com.example.tikoshopping.Service.LoginResult;
import com.example.tikoshopping.Service.User;
import com.example.tikoshopping.Service.UserData;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment,container,false);

        Login();
        return view;

    }

    private void getAllUser (){
        APIService.apiService.getAllUser().enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData listUser = response.body();
                if(listUser!= null){
                    Log.e("API: " ,"Successful");
                    Log.e("User1: " , listUser.getUserList().get(0).getUserName() );
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("Call API Error: "  ,t.getMessage().toString());
                Log.e("Call API" , " Is Error");
            }
        });
    }

    private void Login ()
    {
        Login login = new Login("admin", "admin");
        APIService.apiService.Login(login).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult result = response.body();
                if(result != null && result.getResult()){
                    Log.e("Token: ", result.getToken());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("API error: " , t.getMessage());
            }
        });
    }
}