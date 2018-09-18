package edu.gwu.trivia

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.nio.charset.Charset
import java.util.*

object Utilities {

    private const val TAG = "Utilities"

    //given a filename, read contents as a string
    fun AssetManager.fileAsString(filename: String): String {
        return open(filename).use {
            it.readBytes().toString(Charset.defaultCharset())
        }
    }

    fun loadGameData(fileName: String, context: Context): GameData {
        val questions = ArrayList<Question>()
        var triviaCategory = ""

        val randomGenerator = Random()

        try {
            //read csv file as a string, then parse out new lines to generate question/answer data
            val csvString = context.assets.fileAsString(fileName)
            val lines = csvString.split(",\r".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            triviaCategory = lines[0]

            for (i in 1..lines.size - 1) {
                val wrongAnswers = ArrayList<Answer>()

                while (wrongAnswers.size < 3) {
                    val randomIndex = randomGenerator
                            .nextInt(lines.size - 1) + 1
                    val answerString = lines[randomIndex]

                    if (randomIndex != i && !wrongAnswers.map { it.answer }.contains(answerString)) {
                        wrongAnswers.add(Answer(answerString, false))
                    }
                }

                // create model objects
                val correctAnswer = Answer(lines[i], true)
                Collections.shuffle(wrongAnswers)

                val question = Question(wrongAnswers,correctAnswer)

                questions.add(question)
            }

        } catch (e: Exception) {
            Log.e("generateQuestions", e.message)
        }

        return GameData(questions, triviaCategory)
    }

//    fun parseURLFromBingJSON(jsonObject: JsonObject, desiredOrientation: Int): URL? {
//        //parse through JSON to obtain an image url that meets criteria: not too large, and correct orientation
//        val imageResults = jsonObject.getAsJsonArray("value")
//        if (imageResults != null && imageResults.size() > 0) {
//            for (i in 0..imageResults.size() - 1) {
//                val imageResult = imageResults.get(i).asJsonObject
//                val tooBig = Integer.parseInt(imageResult.get("contentSize").asString.replace(" B", "")) > SyncStateContract.Constants.MAX_IMAGE_FILE_SIZE_IN_BYTES
//
//                if (!tooBig) {
//                    val width = imageResult.get("width").asInt
//                    val height = imageResult.get("height").asInt
//
//                    if (desiredOrientation == Configuration.ORIENTATION_PORTRAIT) {
//                        if (height > width) {
//                            return URL(imageResult.get("contentUrl").asString)
//                        }
//                    } else if (desiredOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//                        if (width > height) {
//                            return URL(imageResult.get("contentUrl").asString)
//                        }
//                    }
//                }
//            }
//        }
//
//        Log.e(TAG, "No image results found")
//        return null
//    }

}