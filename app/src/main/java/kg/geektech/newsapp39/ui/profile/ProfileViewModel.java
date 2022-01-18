package kg.geektech.newsapp39.ui.profile;

import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private Integer avatar;

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public ProfileViewModel(Integer avatar) {
        this.avatar = avatar;
    }
}