package pt.ipbeja.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipbeja.chatapp.databinding.FragmentAddContactBinding
import pt.ipbeja.chatapp.db.Contact
import pt.ipbeja.chatapp.db.ChatDatabase

class AddContactFragment : Fragment() {

    private lateinit var binding: FragmentAddContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentAddContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addContact.setOnClickListener {
            val contactName = binding.name.text.toString()
            val contact = Contact(contactName)

            ChatDatabase(requireContext())
                .contactDao()
                .insert(contact)

            findNavController().popBackStack()
        }
    }
}