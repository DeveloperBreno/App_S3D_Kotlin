package br.com.impacta.ope


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso



class VeiculosAdapter (
    val carros: List<Veiculos>,
    val onClick: (Veiculos) -> Unit): RecyclerView.Adapter<VeiculosAdapter.CarrosViewHolder>() {

    // ViewHolder com os elemetos da tela
    class CarrosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_CarrosDapter)

        }

    }

    // Quantidade de disciplinas na lista

    override fun getItemCount() = this.carros.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carros, parent, false)

        // retornar ViewHolder
        val holder = CarrosViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val context = holder.itemView.context
        val carros = carros[position]
        holder.cardNome.text = carros.nomeModelo
        holder.cardProgress.visibility = View.VISIBLE
        Picasso.with(context).load(carros.foto).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(carros)}
    }
}
