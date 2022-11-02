package pt.ipbeja.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import pt.ipbeja.chatapp.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment() {

    companion object {
        const val REQUEST_KEY = "ADD_CONTACT"
        const val NAME_KEY = "CONTACT_NAME"
    }

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
            val bundlePair = NAME_KEY to contactName // Pair<String, String> -> um par Chave/Valor
            setFragmentResult(REQUEST_KEY, bundleOf(bundlePair))
            findNavController().popBackStack()
        }
    }
}