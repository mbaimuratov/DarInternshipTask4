package com.example.android.darinternshiptask4.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.darinternshiptask4.R
import com.example.android.darinternshiptask4.User
import com.example.android.darinternshiptask4.UsersStorage
import com.example.android.darinternshiptask4.databinding.FragmentGameBinding


class GameFragment : Fragment(R.layout.fragment_game), View.OnClickListener {

    private var usersStorage: UsersStorage = UsersStorage.getInstance()!!

    private lateinit var bindingGame: FragmentGameBinding

    private lateinit var cells: ArrayList<ArrayList<ImageView>>

    private var firstsTurn = true

    private var step = 0

    private lateinit var winnerTextView: TextView

    private lateinit var firstUserName: String
    private lateinit var secondUserName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        step = 0
        cells = ArrayList()

        bindingGame = FragmentGameBinding.bind(view)
        winnerTextView = bindingGame.whoWonTv

        firstUserName = GameFragmentArgs.fromBundle(requireArguments()).firstUserNameString
        secondUserName = GameFragmentArgs.fromBundle(requireArguments()).secondUserNameString

        if (firstsTurn) {
            winnerTextView.text = "$firstUserName's turn"
        } else {
            winnerTextView.text = "$secondUserName's turn"
        }

        for (i in 0..2) {
            val innerList = ArrayList<ImageView>()
            for (j in 0..2) {
                val cellID = "cell_$i$j"
                val resID = resources.getIdentifier(cellID, "id", activity?.packageName)
                val cell: ImageView = activity?.findViewById(resID)!!
                cell.tag = "e"
                cell.setOnClickListener(this)
                innerList.add(cell)
            }
            cells.add(innerList)
        }

        bindingGame.actionContinue.setOnClickListener {
            val user1 = User(firstUserName, 0, 0)
            val user2 = User(secondUserName, 0, 0)

            addOrUpdateUsers(user1, user2)

            findNavController().navigate(R.id.action_gameFragment_to_recordsFragment)
        }
    }

    private fun addOrUpdateUsers(user1: User, user2: User) {
        if (step != 9) {
            if (usersStorage.hasUser(user1)) {
                if (firstsTurn)
                    usersStorage.getUser(user1).winCount++
                else
                    usersStorage.getUser(user1).loseCount++
            } else {
                if (firstsTurn) {
                    user1.winCount++
                }
                else {
                    user1.loseCount++
                }
                usersStorage.addUser(user1)
            }

            if (usersStorage.hasUser(user2)) {
                if (firstsTurn)
                    usersStorage.getUser(user2).loseCount++
                else
                    usersStorage.getUser(user2).winCount++
            } else {
                if (firstsTurn) {
                    user2.loseCount++
                }
                else {
                    user2.winCount++
                }
                usersStorage.addUser(user2)
            }
        } else {
            if (!usersStorage.hasUser(user1)) {
                usersStorage.addUser(user1)
            }
            if (!usersStorage.hasUser(user2)) {
                usersStorage.addUser(user2)
            }
        }
    }


    override fun onClick(v: View?) {
        if ((v as ImageView).tag != "e") {
            return
        }

        if (firstsTurn) {
            v.run {
                setBackgroundColor(Color.WHITE)
                setImageResource(R.drawable.x)
                tag = "x"
            }
            winnerTextView.text = "$secondUserName's turn"
        } else {
            v.run {
                setBackgroundColor(Color.WHITE)
                setImageResource(R.drawable.o)
                tag = "o"
            }
            winnerTextView.text = "$firstUserName's turn"
        }

        step++

        if (isGameOver()) {
            bindingGame.actionContinue.visibility = View.VISIBLE

            if (firstsTurn) {
                winnerTextView.text = "$firstUserName won"
            } else {
                winnerTextView.text = "$secondUserName won"
            }
        }
        else if (step == 9) {
            winnerTextView.text = "No one won"
            bindingGame.actionContinue.visibility = View.VISIBLE
        }
        else {
            firstsTurn = !firstsTurn
        }
    }

    private fun isGameOver(): Boolean {
        for (i in 0..2) {
            if (cells[i][0].tag == cells[i][1].tag && cells[i][0].tag == cells[i][2].tag && cells[i][0].tag != "e") {
                for (j in 0..2) {
                    cells[i][j].setBackgroundResource(R.drawable.borders)
                }
                return true
            }
        }
        for (i in 0..2) {
            if (cells[0][i].tag == cells[1][i].tag && cells[0][i].tag == cells[2][i].tag && cells[0][i].tag != "e") {
                for (j in 0..2) {
                    cells[j][i].setBackgroundResource(R.drawable.borders)
                }
                return true
            }
        }
        if (cells[0][0].tag == cells[1][1].tag && cells[0][0].tag == cells[2][2].tag && cells[0][0].tag != "e") {
            for (i in 0..2) {
                cells[i][i].setBackgroundColor(Color.GREEN)
            }
            return true
        }
        return if (cells[0][2].tag == cells[1][1].tag && cells[0][2].tag == cells[2][0].tag && cells[0][2].tag != "e") {
            cells[0][2].setBackgroundResource(R.drawable.borders)
            cells[1][1].setBackgroundResource(R.drawable.borders)
            cells[2][0].setBackgroundResource(R.drawable.borders)
            true
        } else {
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        firstsTurn = true
    }
}