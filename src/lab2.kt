import org.junit.Test
import kotlin.test.*

/***
 * Javier Chavez
 * 21016
 */

class LabTest {
    @Test
    fun `Given a list, whem it has multiple elements, then result list should be correct`() {
        val result = processList( listOf(10, "Enero", null, true) )
        assertNotNull(result)
        assertTrue(result.size == 3)
        assertTrue(
            result.get(0).originalPos == 0 &&
            result.get(0).originalValue == 10 && 
            result.get(0).type?.lowercase() == "entero" && 
            result.get(0).info?.lowercase() == "m10"
        )

        assertTrue(
            result.get(1).originalPos == 1 && 
        	result.get(1).originalValue == "Enero" &&
         	result.get(1).type?.lowercase() == "cadena" &&
        	result.get(1).info?.lowercase() == "l5"
        )
        assertTrue(
            result.get(2).originalPos == 3 &&
            result.get(2).originalValue == true &&
            result.get(2).type?.lowercase() == "booleano" &&
            result.get(2).info?.lowercase() == "verdadero"
        )
    }

    @Test
    fun `Given a null list, whem processList is called, then it returns null`() {
        val result = processList(null)
        assertNull(result)
    }

    @Test
    fun `Given a list, whem it has only null items, then it returns an empty list`() {
        // Case 1: multiple null items
        val result = processList(listOf(null, null, null, null, null))
        assertNotNull(result)
        assertTrue(result.size == 0)

        // Case 2: empty list
        val result2 = processList(emptyList())
        assertNotNull(result2)
        assertTrue(result2.size == 0)
    }

    @Test
    fun `Given a list, whem it has multiple elements, then result list should be correct in all values`() {
        val result = processList( listOf(20, 25, 2, 7, "hola", "", true, false, null, 2.0 )
        )
        assertNotNull(result)
        assertTrue(result.size == 9)

        assertTrue(
            result.get(0).originalPos == 0 &&
            result.get(0).originalValue == 20 &&
            result.get(0).type?.lowercase() == "entero" &&
            result.get(0).info?.lowercase() == "m10"
        )

        assertTrue(
            result.get(1).originalPos == 1 &&
            result.get(1).originalValue == 25 &&
            result.get(1).type?.lowercase() == "entero" &&
            result.get(1).info?.lowercase() == "m5"
        )

        assertTrue(
            result.get(2).originalPos == 2 &&
            result.get(2).originalValue == 2 &&
            result.get(2).type?.lowercase() == "entero" &&
            result.get(2).info?.lowercase() == "m2"
        )

        assertTrue(
            result.get(3).originalPos == 3 &&
            result.get(3).originalValue == 7 &&
           	result.get(3).type?.lowercase() == "entero" &&
            result.get(3).info == null
        )

        assertTrue(
            result.get(4).originalPos == 4 &&
            result.get(4).originalValue == "hola" &&
            result.get(4).type?.lowercase() == "cadena" &&
            result.get(4).info?.lowercase() == "l4"
        )

        assertTrue(
            result.get(5).originalPos == 5 &&
            result.get(5).originalValue == "" &&
            result.get(5).type?.lowercase() == "cadena" &&
            result.get(5).info?.lowercase() == "l0"
        )

        assertTrue(
            result.get(6).originalPos == 6 &&
            result.get(6).originalValue == true &&
            result.get(6).type?.lowercase() == "booleano" &&
            result.get(6).info?.lowercase() == "verdadero"
        )

        assertTrue(
            result.get(7).originalPos == 7 &&
            result.get(7).originalValue == false &&
            result.get(7).type?.lowercase() == "booleano" &&
            result.get(7).info?.lowercase() == "falso"
        )

        assertTrue(
            result.get(8).originalPos == 9 &&
            result.get(8).originalValue == 2.0 &&
            result.get(8).type == null &&
            result.get(8).info == null
        )
    }
}

// No tocar esta clase ---
data class ItemData(
    var originalPos: Int,
    var originalValue: Any,
    var type: String? = null,
    var info: String? = null
)
// -----------------------

fun main() {
    println("21016 Javier Chavez")
}

fun processList(inputList: List<Any?>?): List<ItemData>? {
    
    val result = ArrayList<ItemData>()
   
    if ( inputList == null ) {
        return null
    }
    
    if ( inputList.isEmpty() ) {
        return result
    }
	
    for ((index, value) in inputList.withIndex()) {
        if(value != null){
            result.add( ItemData(index, value , setType(value) , setInfo(value) ) )
        }
    }
    return result
}

fun setType(value : Any?) : String? {
    return when (value) {
        is Int -> "entero"
        is String -> "cadena"
        is Boolean -> "booleano"
        else -> return null
    }
}

fun intInfo(value : Int) : String? {
   	val result = if (value % 10 == 0){
        "M10"
    } else if (value % 5 == 0){
        "M5"
    } else if (value % 2 == 0){
        "M2"
    } else {
        null
    }
    return result
}

fun setInfo(value : Any?) : String? {
    return when (value) {
        is Int -> intInfo(value)
        is String -> "L"+value.length
        true -> "Verdadero"
        false -> "Falso"
        else -> null
    }

}