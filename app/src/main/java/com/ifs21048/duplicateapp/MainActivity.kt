package com.ifs21048.duplicateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.ifs21048.duplicateapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
    }
    private fun setupView() {
        binding.navView.setCheckedItem(R.id.nav_inbox)
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_CHATS)
    }

    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Camera!")
                    true
                }
                R.id.action_settings -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Settings!")
                    true
                }
                R.id.action_logout -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Logout!")
                    true
                }
                else -> true
            }
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_inbox -> {
                    loadFragment(FLAG_FRAGMENT_CHATS)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_primary -> {
                    loadFragment(FLAG_FRAGMENT_UPDATES, "Memilih menu Updates!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_promotions -> {
                    loadFragment(FLAG_FRAGMENT_COMMUNITIES, "Memilih menu Communities!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_social -> {
                    loadFragment(FLAG_FRAGMENT_CALLS, "Memilih menu Calls!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> true
            }
        }

        binding.inAppBar.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_chats -> {
                    loadFragment(FLAG_FRAGMENT_CHATS)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.navigation_updates -> {
                    loadFragment(FLAG_FRAGMENT_UPDATES)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.navigation_communities -> {
                    loadFragment(FLAG_FRAGMENT_COMMUNITIES)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.navigation_calls -> {
                    loadFragment(FLAG_FRAGMENT_CALLS)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> true
            }
        }
    }

    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId = binding.inAppBar.inContentMain.frameContainer.id

        when (flag) {
            FLAG_FRAGMENT_CHATS -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_chats)
                    .setChecked(true)
                val chatFragment = ChatsFragments()
                val bundle = Bundle().apply {
                    this.putString(
                        ChatsFragments.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                chatFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        chatFragment,
                        chatFragment::class.java.simpleName
                    )
                    .commit()
            }

            FLAG_FRAGMENT_UPDATES -> {
                val updatesFragment = UpdatesFragment()
                val fragment =
                    fragmentManager.findFragmentByTag(UpdatesFragment::class.java.simpleName)

                if (fragment !is UpdatesFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            updatesFragment,
                            UpdatesFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

            FLAG_FRAGMENT_CALLS -> {
                val callsFragment = CallsFragment()
                val fragment =
                    fragmentManager.findFragmentByTag(CallsFragment::class.java.simpleName)

                if (fragment !is CallsFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            callsFragment,
                            CallsFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

            FLAG_FRAGMENT_COMMUNITIES -> {
                val communitiesFragment = CommunitiesFragment()
                val fragment =
                    fragmentManager.findFragmentByTag(CommunitiesFragment::class.java.simpleName)

                if (fragment !is CommunitiesFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            communitiesFragment,
                            CommunitiesFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }
    companion object {
        const val FLAG_FRAGMENT_CHATS = "fragment_chats"
        const val FLAG_FRAGMENT_UPDATES = "fragment_updates"
        const val FLAG_FRAGMENT_COMMUNITIES = "fragment_communities"
        const val FLAG_FRAGMENT_CALLS = "fragment_calls"
    }
}
