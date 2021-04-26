package ru.navodnikov.denis.karagatantour.ui.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.FragmentContactsBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import ru.navodnikov.denis.karagatantour.ui.utils.Intents
import ru.navodnikov.denis.karagatantour.ui.utils.Intents.*


class ContactsFragment : BaseFragment<FragmentContactsBinding>(),
    ContactsContract.View, View.OnClickListener {

    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentContactsBinding.inflate(inflater, container, false)
        fragmentBinding?.locationIcon?.setOnClickListener(this)
        fragmentBinding?.mailIcon?.setOnClickListener(this)
        fragmentBinding?.whatsappIcon?.setOnClickListener(this)
        fragmentBinding?.telegramIcon?.setOnClickListener(this)
        fragmentBinding?.instagramIcon?.setOnClickListener(this)
        fragmentBinding?.facebookIcon?.setOnClickListener(this)
        fragmentBinding?.websiteIcon?.setOnClickListener(this)
        fragmentBinding?.vkIcon?.setOnClickListener(this)
        return fragmentBinding?.root
    }

    override fun onClick(view: View?) {
        Log.d("TAG", "onClick: ")
        when (view) {
            fragmentBinding?.locationIcon -> getOpenIntent(GEO)
            fragmentBinding?.mailIcon -> getOpenIntent(MAIL)
            fragmentBinding?.whatsappIcon -> getOpenIntent(WHATSAPP)
            fragmentBinding?.telegramIcon -> getOpenIntent(TELEGRAM)
            fragmentBinding?.instagramIcon -> getOpenIntent(INSTAGRAM)
            fragmentBinding?.facebookIcon -> getOpenIntent(FACEBOOK)
            fragmentBinding?.websiteIcon -> getOpenIntent(WEBSITE)
            fragmentBinding?.vkIcon -> getOpenIntent(VK)
        }
    }

    override fun getOpenIntent(intent: Intents) {
        var url = 0
        when (intent) {
            GEO -> url = R.string.url_geo
            MAIL -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse(getString(R.string.mailto))
                }
                if (activity?.packageManager?.let { intent.resolveActivity(it) } != null) {
                    startActivity(intent)
                }
                return
            }
            WHATSAPP -> url = R.string.url_whatsapp
            TELEGRAM -> url = R.string.url_telegram
            INSTAGRAM -> url = R.string.url_instagram
            FACEBOOK -> url = R.string.url_facebook
            WEBSITE -> url = R.string.url_website
            VK -> url = R.string.url_vk
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(url)))
        if (activity?.let { intent.resolveActivity(it.packageManager) } != null) {
            startActivity(intent)
        }


    }


}