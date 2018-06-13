package com.castro.andres.cnntakehome


import com.castro.andres.cnntakehome.data.entities.WeatherForecast
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
    fun cityName_Missing()
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
                "\"cod\": 200\n" +
                "}"

        val weatherForecast = QueryResponseToWeatherParser.parseCurrentJson(testString)
        val expected = "n/a"

        assertEquals(expected, weatherForecast.city)
    }

    @Test
    fun cityName_Present()
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
        val expected = "Atlanta"
        assertEquals(expected, weatherForecast.city)
    }

    @Test
    fun utcTimeStamp_Present()
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
        val expected = 1461591863L
        assertEquals(expected, weatherForecast.date)

    }

    @Test
    fun utcTimeStamp_Missing()
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
        val expected = Long.MIN_VALUE
        assertEquals(expected, weatherForecast.date)

    }

    @Test
    fun mainObject_FullyPresent()
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

        val expectedTemp = 64.83
        val expectedHumidity = 72.0
        val expectedPressure = 1019.0
        val expectedMinTemp = 62.01

        assertEquals(expectedTemp, weatherForecast.currentTemp, 0.01)
        assertEquals(expectedHumidity, weatherForecast.humidity, 0.01)
        assertEquals(expectedPressure, weatherForecast.pressure, 0.01)
        assertEquals(expectedMinTemp, weatherForecast.minTemp, 0.01)


    }

    @Test
    fun mainObject_MissingTemp()
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

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecast.currentTemp, 0.1)
    }

    @Test
    fun mainObject_MissingHumidity()
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

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecast.humidity, 0.1)

    }

    @Test
    fun mainObject_MissingPressure()
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

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecast.pressure, 0.1)


    }

    @Test
    fun mainObject_minTemp(){

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

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecast.minTemp, 0.1)


    }

    @Test
    fun mainObject_FullyMissing()
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

        val expectedTemp = Double.MIN_VALUE
        val expectedHumidity = Double.MIN_VALUE
        val expectedPressure = Double.MIN_VALUE
        val expectedMinTemp = Double.MIN_VALUE

        assertEquals(expectedTemp, weatherForecast.currentTemp, 0.01)
        assertEquals(expectedHumidity, weatherForecast.humidity, 0.01)
        assertEquals(expectedPressure, weatherForecast.pressure, 0.01)
        assertEquals(expectedMinTemp, weatherForecast.minTemp, 0.01)

    }

    @Test
    fun windObject_FullyPresent()
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
        val expectedSpeed =  5.82
        val expectedDegree = 200.0
        assertEquals(expectedSpeed, weatherForecast.wind, 0.001)
        assertEquals(expectedDegree, weatherForecast.windDegree, 0.001)

    }

    @Test
    fun windObject_MissingWindSpeed()
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
        val expectedSpeed =  Double.MIN_VALUE
        assertEquals(expectedSpeed, weatherForecast.wind, 0.001)

    }

    @Test
    fun windObject_MissingDegrees()
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
                "\"speed\": 5.82\n" +
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
        val expectedDegree =  Double.MIN_VALUE
        assertEquals(expectedDegree, weatherForecast.windDegree, 0.001)
    }

    @Test
    fun windObject_FullyMissing()
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
        val expectedSpeed =  Double.MIN_VALUE
        val expectedDegree = Double.MIN_VALUE
        assertEquals(expectedSpeed, weatherForecast.wind, 0.001)
        assertEquals(expectedDegree, weatherForecast.windDegree, 0.001)

    }

}
