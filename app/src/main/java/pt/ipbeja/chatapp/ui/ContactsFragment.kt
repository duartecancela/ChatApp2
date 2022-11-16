package pt.ipbeja.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import pt.ipbeja.chatapp.databinding.ContactItemBinding
import pt.ipbeja.chatapp.databinding.FragmentContactsBinding
import pt.ipbeja.chatapp.model.Contact
import pt.ipbeja.chatapp.model.ContactDatabase


class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    val adapter = ContactsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contactList.adapter = this.adapter

        val todos = ContactDatabase(requireContext())
            .contactDao()
            .getAll()

        adapter.data = todos as MutableList<Contact>

        binding.addContact.setOnClickListener {
            findNavController().navigate(ContactsFragmentDirections.actionContactsFragmentToAddContactFragment())
        }

    }

    inner class ContactViewHolder(private val binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var contact: Contact
        init {
            binding.root.setOnLongClickListener {

                val id: Long = contact.id
                val position: Int = adapterPosition

                Snackbar.make(it, "'${contact.name}' has been deleted.", Snackbar.LENGTH_SHORT).show()
                adapter.remove(id, position)
                true // devolvemos true se tratamos deste evento
            }

            binding.root.setOnClickListener {
                // TODO avançar para um ChatFragment
            }
        }

        fun bind(contact: Contact) {
            this.contact = contact
            binding.name.text = contact.name
        }
    }


    inner class ContactsAdapter(contacts: List<Contact> = mutableListOf()) :
        RecyclerView.Adapter<ContactViewHolder>() {

        var data: MutableList<Contact> = mutableListOf()

        init {
            data.addAll(contacts)
        }

        /**
         * Delete object from database
         * @param id    primary key of row database
         * @param position  object position at recycler view
         */
        fun remove(id: Long, position: Int) {

            ContactDatabase(requireContext())
                .contactDao()
                .delete(id)

            data.removeAt(position)
            notifyItemRemoved(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ContactItemBinding.inflate(layoutInflater, parent, false)
            return ContactViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = data[position]
            holder.bind(contact)
        }

        override fun getItemCount() = data.size

    }

}