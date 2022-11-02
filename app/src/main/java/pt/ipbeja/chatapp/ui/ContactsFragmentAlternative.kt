package pt.ipbeja.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import pt.ipbeja.chatapp.databinding.ContactItemBinding
import pt.ipbeja.chatapp.databinding.FragmentContactsBinding
import pt.ipbeja.chatapp.model.Contact


/**
 * Esta é uma alternativa ao ContactsFragment realizado em aula, evitando as inner classes e,
 * por conseguinte, as referência implicitas entre as classes.
 * Esta solução baseia-se em passar callbacks (onContactClicked, onContactLongClicked) para o adapter
 * e do adapter para o ViewHolder.
 * Assim, o Adapter e o ViewHolder não mantêm uma referência ao Fragment.
 *
 * Para experimentar esta solução, terá de descomentar e adicionar este Fragment ao grafo de navegação e torná-lo
 * o home fragment (start destination)
 *
 */

/*

class ContactsFragmentAlternative : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    // ContactsAdapter recebe como argumento uma referência para uma função que recebe um Int (a posição): (Int) -> Unit
    // Recebe também uma função para o longClick (Int) -> Boolean
    private val adapter = ContactsAdapter(this::onContactClicked, this::onContactLongClicked)


    private var idCounter: Long = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.contactList.adapter = adapter

        setFragmentResultListener(AddContactFragment.REQUEST_KEY) { _, bundle ->
            val name = bundle.getString(AddContactFragment.NAME_KEY)
            if (name != null) {
                adapter.add(Contact(idCounter++, name))
            }
        }

        binding.addContact.setOnClickListener {
            findNavController().navigate(ContactsFragmentDirections.actionContactsFragmentToAddContactFragment())
        }

    }

    private fun onContactClicked(position: Int) {
        // avançar para a lista de mensagens (a fazer)
    }


    private fun onContactLongClicked(position: Int) : Boolean {
        // apagar o contacto da lista
        return true
    }


    class ContactViewHolder(private val binding: ContactItemBinding, private val onClick: (Int) -> Unit, private val onLongClick: (Int) -> Boolean) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(adapterPosition)
            }

            binding.root.setOnLongClickListener {
                return@setOnLongClickListener onLongClick(adapterPosition)
            }


        }

        fun bind(contact: Contact) {
            binding.name.text = contact.name
        }
    }


    class ContactsAdapter(private val onClick: (Int) -> Unit, private val onLongClick: (Int) -> Boolean) : RecyclerView.Adapter<ContactViewHolder>() {

        private val _data: MutableList<Contact> = mutableListOf() // internamente é mutável
        val data: List<Contact> = _data // mas expomos lista (public) como imutável para evitar alterações à mesma fora do Adapter

        constructor(contacts: List<Contact>, onClick: (Int) -> Unit, onLongClick: (Int) -> Boolean) : this(onClick, onLongClick) {
            // Podemos ter um construtor secundário que recebe logo uma lista de contactos inicial
            _data.addAll(contacts)
        }

        fun add(contact: Contact) {
            _data.add(contact)
            notifyItemInserted(_data.lastIndex)
        }

        fun remove(position: Int) {
            _data.removeAt(position)
            notifyItemRemoved(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ContactItemBinding.inflate(layoutInflater, parent, false)
            return ContactViewHolder(binding, onClick, onLongClick)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = _data[position]
            holder.bind(contact)
        }

        override fun getItemCount() = _data.size

    }


}*/
