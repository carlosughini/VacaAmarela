package com.vacaamarela.carlos.vacaamarela

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    // Create a list of butcherys
    private var butcheryList = ArrayList<Estabelecimento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the actionBar
        val actionBar = supportActionBar
        // Change actionBar title
        actionBar!!.title = "Açougues"

        // Insert in the list of butcherys butchery data
        butcheryList.add(Estabelecimento("0.6","Casa de Carnes São Jorge"))
        butcheryList.add(Estabelecimento("1.2","Casa de Carnes Santo Ângelo"))
        butcheryList.add(Estabelecimento("1.6","Açougue e Frigorífico Novilho Nobre"))
        butcheryList.add(Estabelecimento("1.8","Açougue San Remo"))
        butcheryList.add(Estabelecimento("2.1","São Gabriel Carnes"))
        butcheryList.add(Estabelecimento("2.4","Casa de Carnes Bela Vista"))
        butcheryList.add(Estabelecimento("2.8","Casa de Carnes Mania do Gaúcho"))
        butcheryList.add(Estabelecimento("3.4","Casa de Carnes Elite"))
        butcheryList.add(Estabelecimento("4.1","Açougue Big Bife"))

        // Find the ListView object in the view hierarchy of the Activity.
        val listView : ListView = findViewById(R.id.listView)

        /**
         * Create an ButcheryAdapter, whose data source is a list of Butcherys.
         * The adapter knows how to create list items for each item in the list.
         */
        val butcheryAdapter = EstabelecimentoAdapter(this,butcheryList)

        /**
         * Make the listView use the butcheryAdapter created above, so that the
         * listView will display list items for each Butchery in the list
         */
        listView.adapter = butcheryAdapter
    }
}
