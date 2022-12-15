    package com.samansepahvand.chatappfinal.activities;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.iid.FirebaseInstanceId;
    import com.samansepahvand.chatappfinal.databinding.ActivitySignInBinding;

    import java.util.HashMap;
    import java.util.Map;


    public class SignInActivity extends AppCompatActivity {

        private static final String TAG = "FCM";
        String newToken="null";
        private ActivitySignInBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding=ActivitySignInBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            setListeners();
        }
        private void setListeners(){
            binding.textCreateNewAccount.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            });

            binding.buttonSignIn.setOnClickListener(view -> {addDataToFireBase();});
            getToken();

        }

        private String getToken() {

            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                newToken = instanceIdResult.getToken();
                Log.e(TAG, newToken);
            });

            return newToken;
        }
        private void addDataToFireBase(){

            try{

                Log.e(TAG, "addDataToFireBase click ");

            FirebaseFirestore dataBase=FirebaseFirestore.getInstance();

            HashMap<String,Object> data=new HashMap<>();
            data.put("first_name","Saman");
            data.put("last_name","Sepahvand");

// Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

// Add a new document with a generated ID
                dataBase.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });



            }catch (Exception e){
                Log.e(TAG, "addDataToFireBase: "+e.getMessage());
            }
        }

    }