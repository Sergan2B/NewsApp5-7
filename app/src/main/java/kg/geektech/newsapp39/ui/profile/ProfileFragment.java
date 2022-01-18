package kg.geektech.newsapp39.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kg.geektech.newsapp39.databinding.ProfileFragmentBinding;

public class ProfileFragment extends Fragment {

    private ProfileFragmentBinding binding;

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
    }

    private void initSetAvatarImage() {
        //binding.avatarProfile.setOnClickListener(view2 ->takePhoto());
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), imageUri -> binding.avatarProfile.setImageURI(imageUri));
        binding.avatarProfile.setOnClickListener(view1 -> mGetContent.launch("image/*"));
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
