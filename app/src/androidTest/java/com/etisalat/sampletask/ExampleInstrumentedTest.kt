package com.etisalat.sampletask

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.etisalat.sampletask.model.Item
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.etisalat.sampletask", appContext.packageName)
    }

    @Test
    fun testSorting() {
        var item: Item = Item()
        var itemsList: ArrayList<Item> = ArrayList()
        var itemsListSorted: ArrayList<Item> = ArrayList()
        item.name = "Margherita"
        itemsList.add(item)
        itemsListSorted.add(item)
        item.name = "Double Cheese Margherita"
        itemsList.add(item)
        itemsListSorted.add(item)
        item.name = "Fresh Veggie"
        itemsList.add(item)
        itemsListSorted.add(item)

        Collections.sort(itemsListSorted, { item, t1 ->
            val s1 = item.name
            val s2 = t1.name
            s1.compareTo(s2, ignoreCase = true)
        })
//        Assert.assertArrayEquals(itemsListSorted, itemsList );


        Assert.assertThat(itemsListSorted, IsNot.not(IsEqual.equalTo(itemsList)));
//        assertTrue(Arrays.equals(itemsListSorted, itemsList));
    }
}
