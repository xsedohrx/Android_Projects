package com.example.level_03_task1_userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_profile.*


class CreateProfileActivity : AppCompatActivity() {
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initViews()
    }

    private fun initViews() {
        btnGallery.setOnClickListener { onGalleryClick() }
        btnConfirm.setOnClickListener { onConfirmClick() }
    }

    private fun onGalleryClick() {
        // Create an Intent with action as ACTION_PICK
        val galleryIntent = Intent(Intent.ACTION_PICK)

        // Sets the type as image/*. This ensures only components of type image are selected
        galleryIntent.type = "image/*"

        // Start the activity using the gallery intent
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    /**
     * Create a [Profile] object start the [ProfileActivity] and parse the [Profile] object to it.
     */
    private fun onConfirmClick() {
        val profile = Profile(
            etFirstName.text.toString(),
            etLastName.text.toString(),
            etProfileDescription.text.toString(),
            profileImageUri
        )

        val profileActivityIntent = Intent(this, ProfileActivity::class.java)
        profileActivityIntent.putExtra(ProfileActivity.PROFILE_EXTRA, profile)
        startActivity(profileActivityIntent)
    }

    /**
     * When a [GALLERY_REQUEST_CODE] has been found store the uri in [profileImageUri] and set the imageView with it.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    profileImageUri = data?.data
                    ivProfile.setImageURI(profileImageUri)
                }
            }
        }
    }

    companion object {
        const val GALLERY_REQUEST_CODE = 100
    }
}
