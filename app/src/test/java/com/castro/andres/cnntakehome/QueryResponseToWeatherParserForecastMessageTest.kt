import com.castro.andres.cnntakehome.data.parser.QueryResponseToWeatherParser
import org.junit.Assert.assertEquals
import org.junit.Test

class QueryResponseToWeatherParserForecastMessageTest {


    @Test
    fun weatherObject_FullyMissing()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
        val results = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedDescription = "n/a"
        val expectedIconId = "n/a"
        assertEquals(expectedDescription ,results[0].dayDescription)
        assertEquals(expectedIconId ,results[0].iconId)
    }

    @Test
    fun weatherObject_MissingIconID()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
        val results = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedIconId = "n/a"
        assertEquals(expectedIconId, results[0].iconId)
    }

        @Test
        fun weatherObject_MissingDescription()
        {
            val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
            val results = QueryResponseToWeatherParser.parseForecastJson(testString)
            val expectedDescription = "n/a"
            assertEquals(expectedDescription, results[0].dayDescription)
        }

    @Test
    fun description_FullyPresent()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedDescription = "moderate rain"
        val expectedIcon = "10n"
        assertEquals(expectedDescription,weatherForecasts[0].dayDescription)
        assertEquals(expectedIcon, weatherForecasts[0].iconId)
    }

    @Test
    fun cityName_Missing()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
        val expected = "n/a"
        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        assertEquals(expected, weatherForecasts[0].city)
    }

    @Test
    fun cityName_Present()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"
        val expected = "Atlanta"
        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        assertEquals(expected, weatherForecasts[0].city)
    }

    @Test
    fun utcTimeStamp_Present()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expected = 1528956000L
        assertEquals(expected, weatherForecasts[0].date)

    }

    @Test
    fun utcTimeStamp_Missing()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expected = Long.MIN_VALUE
        assertEquals(expected, weatherForecasts[0].date)

    }

    @Test
    fun mainObject_FullyPresent()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expectedTemp = 20.0
        val expectedHumidity = 100.0
        val expectedPressure = 997.38
        val expectedMinTemp = 20.0

        assertEquals(expectedTemp, weatherForecasts[0].currentTemp, 0.01)
        assertEquals(expectedHumidity,  weatherForecasts[0].humidity, 0.01)
        assertEquals(expectedPressure,  weatherForecasts[0].pressure, 0.01)
        assertEquals(expectedMinTemp,  weatherForecasts[0].minTemp, 0.01)


    }

    @Test
    fun mainObject_MissingTemp()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"


        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecasts[0].currentTemp, 0.1)
    }

    @Test
    fun mainObject_MissingHumidity()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecasts[0].humidity, 0.1)

    }

    @Test
    fun mainObject_MissingPressure()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecasts[0].pressure, 0.1)


    }

    @Test
    fun mainObject_minTemp(){

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"


        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expected = Double.MIN_VALUE

        assertEquals(expected, weatherForecasts[0].minTemp, 0.1)

    }

    @Test
    fun mainObject_FullyMissing()
    {
        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)

        val expectedTemp = Double.MIN_VALUE
        val expectedHumidity = Double.MIN_VALUE
        val expectedPressure = Double.MIN_VALUE
        val expectedMinTemp = Double.MIN_VALUE

        assertEquals(expectedTemp, weatherForecasts[0].currentTemp, 0.01)
        assertEquals(expectedHumidity, weatherForecasts[0].humidity, 0.01)
        assertEquals(expectedPressure, weatherForecasts[0].pressure, 0.01)
        assertEquals(expectedMinTemp, weatherForecasts[0].minTemp, 0.01)

    }

    @Test
    fun windObject_FullyPresent()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81,\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"


        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedSpeed =  2.81
        val expectedDegree = 260.5
        assertEquals(expectedSpeed, weatherForecasts[0].wind, 0.001)
        assertEquals(expectedDegree, weatherForecasts[0].windDegree, 0.001)

    }

    @Test
    fun windObject_MissingWindSpeed()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"deg\":260.5},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedSpeed =  Double.MIN_VALUE
        assertEquals(expectedSpeed, weatherForecasts[0].wind, 0.001)

    }

    @Test
    fun windObject_MissingDegrees()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.81},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"


        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedDegree =  Double.MIN_VALUE
        assertEquals(expectedDegree,  weatherForecasts[0].windDegree, 0.001)
    }

    @Test
    fun windObject_FullyMissing()
    {

        val testString = "{\"cod\":\"200\",\"message\":0.0045,\"cnt\":5,\"list\":[{\"dt\":1528956000,\"main\":{\"temp\":20,\"temp_min\":20,\"temp_max\":20.06,\"pressure\":997.38,\"sea_level\":1029.96,\"grnd_level\":997.38,\"humidity\":100,\"temp_kf\":-0.06},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"rain\":{\"3h\":10.595},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2018-06-14 06:00:00\"}],\"city\":{\"id\":4180439,\"name\":\"Atlanta\",\"coord\":{\"lat\":33.749,\"lon\":-84.388},\"country\":\"US\"}}"

        val weatherForecasts = QueryResponseToWeatherParser.parseForecastJson(testString)
        val expectedDegree =  Double.MIN_VALUE
        val expectedSpeed =  Double.MIN_VALUE
        assertEquals(expectedSpeed, weatherForecasts[0].wind, 0.001)
        assertEquals(expectedDegree,  weatherForecasts[0].windDegree, 0.001)
    }
}
