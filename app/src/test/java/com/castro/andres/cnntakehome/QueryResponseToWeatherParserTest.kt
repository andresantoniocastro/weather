package com.castro.andres.cnntakehome


import com.castro.andres.cnntakehome.data.parser.QueryResponseToWeatherParser
import org.junit.Assert.*
import org.junit.Test


class QueryResponseToWeatherParserCurrentMessageTest {

    @Test
    fun weatherObject_FullyMissing()
    {
        val testString = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": -84.39,\n" +
                "\"lat\": 33.75\n" +
                "},\n" +
                "\"base\": \"cmc stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 64.83,\n" +
                "\"pressure\": 1019,\n" +
                "\"humidity\": 72,\n" +
                "\"temp_min\": 62.01,\n" +
                "\"temp_max\": 68\n" +
                "},\n" +
                "\"wind\": {\n" +
                "\"speed\": 5.82,\n" +
                "\"deg\": 200\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1461591863,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 748,\n" +
                "\"message\": 0.0044,\n" +
                "\"country\": \"US\",\n" +
                "\"sunrise\": 1461581639,\n" +
                " \"sunset\": 1461629830\n" +
                "},\n" +
                "\"id\": 4180439,\n" +
                "\"name\": \"Atlanta\",\n" +
                "\"cod\": 200\n" +
                "}"

        val weatherForecast = QueryResponseToWeatherParser.parseCurrentJson(testString)
        val expectedDescription = "n/a"
        val expectedIconId = "n/a"
        assertEquals(expectedDescription, weatherForecast.dayDescription)
        assertEquals(expectedIconId, weatherForecast.iconId)
    }

    @Test
    fun weatherObject_MissingIconID()
    {
        val testString = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": -84.39,\n" +
                "\"lat\": 33.75\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"broken clouds\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"cmc stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 64.83,\n" +
                "\"pressure\": 1019,\n" +
                "\"humidity\": 72,\n" +
                "\"temp_min\": 62.01,\n" +
                "\"temp_max\": 68\n" +
                "},\n" +
                "\"wind\": {\n" +
                "\"speed\": 5.82,\n" +
                "\"deg\": 200\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1461591863,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 748,\n" +
                "\"message\": 0.0044,\n" +
                "\"country\": \"US\",\n" +
                "\"sunrise\": 1461581639,\n" +
                " \"sunset\": 1461629830\n" +
                "},\n" +
                "\"id\": 4180439,\n" +
                "\"name\": \"Atlanta\",\n" +
                "\"cod\": 200\n" +
                "}"

        val weatherForecast = QueryResponseToWeatherParser.parseCurrentJson(testString)
        val expectedIconId = "n/a"
        assertEquals(expectedIconId, weatherForecast.iconId)

    }

    @Test
    fun weatherObject_MissingDescription()
    {
        val testString = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": -84.39,\n" +
                "\"lat\": 33.75\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"icon\": \"04d\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"cmc stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 64.83,\n" +
                "\"pressure\": 1019,\n" +
                "\"humidity\": 72,\n" +
                "\"temp_min\": 62.01,\n" +
                "\"temp_max\": 68\n" +
                "},\n" +
                "\"wind\": {\n" +
                "\"speed\": 5.82,\n" +
                "\"deg\": 200\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1461591863,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 748,\n" +
                "\"message\": 0.0044,\n" +
                "\"country\": \"US\",\n" +
                "\"sunrise\": 1461581639,\n" +
                " \"sunset\": 1461629830\n" +
                "},\n" +
                "\"id\": 4180439,\n" +
                "\"name\": \"Atlanta\",\n" +
                "\"cod\": 200\n" +
                "}"

        val weatherForecast = QueryResponseToWeatherParser.parseCurrentJson(testString)
        val expectedDescription = "n/a"
        assertEquals(expectedDescription, weatherForecast.dayDescription)
    }

    @Test
    fun description_FullyPresent()
    {
        val testString = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": -84.39,\n" +
                "\"lat\": 33.75\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 803,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"broken clouds\",\n" +
                "\"icon\": \"04d\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"cmc stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 64.83,\n" +
                "\"pressure\": 1019,\n" +
                "\"humidity\": 72,\n" +
                "\"temp_min\": 62.01,\n" +
                "\"temp_max\": 68\n" +
                "},\n" +
                "\"wind\": {\n" +
                "\"speed\": 5.82,\n" +
                "\"deg\": 200\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 75\n" +
                "},\n" +
                "\"dt\": 1461591863,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 748,\n" +
                "\"message\": 0.0044,\n" +
                "\"country\": \"US\",\n" +
                "\"sunrise\": 1461581639,\n" +
                " \"sunset\": 1461629830\n" +
                "},\n" +
                "\"id\": 4180439,\n" +
                "\"name\": \"Atlanta\",\n" +
                "\"cod\": 200\n" +
                "}"

        val weatherForecast = QueryResponseToWeatherParser.parseCurrentJson(testString)
        val expectedDescription = "broken clouds"
        val expectedIconId = "04d"
        assertEquals(expectedDescription, weatherForecast.dayDescription)
        assertEquals(expectedIconId, weatherForecast.iconId)
    }

    @Test
    fun cityName_Present()
    {

    }

    @Test
    fun cityName_Missing()
    {

    }

    @Test
    fun utcTimeStamp_Present()
    {

    }

    @Test
    fun utcTimeStamp_Missing()
    {

    }

    @Test
    fun mainObject_FullyPresent()
    {

    }

    @Test
    fun mainObject_MissingTemp()
    {

    }

    @Test
    fun mainObject_MissingHumidity()
    {}

    @Test
    fun mainObject_MissingPressure()
    {

    }

    @Test
    fun mainObject_minTemp(){

    }

    @Test
    fun mainObject_FullyMissing()
    {

    }

    @Test
    fun windObject_FullyPresent()
    {

    }

    @Test
    fun windObject_MissingWindSpeed()
    {

    }

    @Test
    fun windObject_MissingDegrees()
    {

    }

    @Test
    fun windObject_FullyMissing()
    {

    }

}
