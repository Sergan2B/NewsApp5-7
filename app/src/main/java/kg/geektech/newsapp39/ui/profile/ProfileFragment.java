package kg.geektech.newsapp39.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import kg.geektech.newsapp39.Prefs;
import kg.geektech.newsapp39.databinding.ProfileFragmentBinding;

public class ProfileFragment extends Fragment {

    //после пятой работы ничего не менялось

    private ProfileFragmentBinding binding;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;
    private Prefs prefs;
    private Uri uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (prefs.getImageUri() != null) uri = Uri.parse(prefs.getImageUri());
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.avatarProfile);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {  //Нужно viewCreated держать в чистоте, использовать только методы
        super.onViewCreated(view, savedInstanceState);
        initSetAvatarImage();
        loadText();
        binding.avatarProfile.setOnClickListener(view1 -> {
            loadAvatar();
        });
    }

    private void saveAvatar() {
        mSettings = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt("KEYING141", binding.avatarProfile.getId());
        editor.apply();
    }

    private void saveText() {
        mSettings = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("KEYING14", binding.nickname.getText().toString());
        editor.apply();
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                uri = result.getData().getData();
                prefs.saveImage(String.valueOf(uri));
                binding.avatarProfile.setImageURI(uri);
            }
        }
    });


    private void loadAvatar() {
        /*mSettings = requireActivity().getPreferences(Context.MODE_PRIVATE);
        int savedAvatar = mSettings.getInt("KEYING141", R.drawable.x6x);
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), imageUri -> binding.avatarProfile.setImageURI(imageUri));*/

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }

    private void loadText() {
        mSettings = requireActivity().getPreferences(Context.MODE_PRIVATE);
        String savedText = mSettings.getString("KEYING14", "");
        binding.nickname.setText(savedText);
    }

    private void initSetAvatarImage() {
        //binding.avatarProfile.setOnClickListener(view2 ->takePhoto());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveAvatar();
        saveText();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.avatarProfile);
    }
}

   /* private void takePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
    }*/
    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == SELECT_IMAGE) {
             if (resultCode == Activity.RESULT_OK) {
                 if (data != null) {
                     try {
                         Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                         binding.avatarProfile.setImageBitmap(bitmap);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             } else if (resultCode == Activity.RESULT_CANCELED)  {
                 Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
             }
         }
     }*/
