package com.ouz.ascrecyclerview02

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ouz.ascrecyclerview02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Adapter.SelectedUser,GridAdap.SelectedUser{
    private lateinit var binding: ActivityMainBinding
    private lateinit var userList:ArrayList<DataType>
    private var selectUser:DataType?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//user eklendi
        userList= ArrayList()
        userList.add(DataType("1","+90 222 222 222",R.drawable.images))
        userList.add(  DataType("2","+90 111 111 111",R.drawable.images2))
        userList.add(DataType("3","+90 555 555 222",R.drawable.images3))
        userList.add(  DataType("4","+90 124 125 111",R.drawable.images4))
        userList.add(DataType("5","+90 222 222 222",R.drawable.images))
        userList.add(  DataType("6","+90 111 111 111",R.drawable.images2))
        userList.add(DataType("7","+90 555 555 222",R.drawable.images3))
        userList.add(  DataType("8","+90 124 125 111",R.drawable.images4))
        userList.add(DataType("9","+90 222 222 222",R.drawable.images))
        userList.add(  DataType("10","+90 111 111 111",R.drawable.images2))
        userList.add(DataType("11","+90 555 555 222",R.drawable.images3))
        userList.add(  DataType("12","+90 124 125 111",R.drawable.images4))
//Adapter Bağlandı
        val userAdapter=Adapter(userList,this@MainActivity)
        val userGridAdapter=GridAdap(userList,this@MainActivity)

        binding.recyclerView.adapter=userAdapter
        binding.recyclerView.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)

//Switche göre durum belirlendi
     binding.switch1.setOnCheckedChangeListener { compoundButton, b ->
                  if (b){
                      binding.recyclerView.adapter=userGridAdapter
                      binding.recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                      binding.recyclerView.setHasFixedSize(true)
                  }
                  else{
                      binding.recyclerView.adapter=userAdapter
                      binding.recyclerView.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                      binding.recyclerView.setHasFixedSize(true)
                  }
              }
//Fab butonu ile Silme işlemi
        binding.DeleteFAB.setOnClickListener {

            var deletedIndex=userList.indexOf(selectUser)
            if (deletedIndex==-1){
                Toast.makeText(this,"Seçim Yapınız...", Toast.LENGTH_SHORT).show()
            }
            else{
                val alert=AlertDialog.Builder(this@MainActivity)
                    alert.setTitle("")
                    alert.setMessage("Are you sure?")
                    alert.setNegativeButton("No",null)
                    alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                        userList.removeAt(deletedIndex)
                        userAdapter.userListUpdate(userList)
                      selectUser=null
                    }).show()
           }
        }
        //Detail activitye transfer sağlandı
          userAdapter.userTransfer=::userT

    }
    //Lambdanın işlemi belirlendi
    private fun userT(select:DataType){
      var intent=Intent(MainActivity@this,DetailActivity::class.java)
        intent.putExtra("user",select)
        startActivity(intent)

    }
//interface eklendi
    override fun selectedUser(dataType: DataType?) {
        selectUser=dataType
     }


}