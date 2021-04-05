package com.vspkg.test;

import com.vspkg.JsonParser;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class JsonParserTest {
    @Test
    public void testEmpty(){
        Assert.assertNull(JsonParser.parseJson(""));
    }
    @Test
    public void testIncorrectJson(){
        String brokenJson = "  isActive: '{{bool()}}',\n" +
                "    balance: '{{floating(1000, 4000, 2, \"$0,0.00\")}}',\n" +
                "    picture: 'http://placehold.it/32x32',\n" +
                "    age: '{{integer(20, 40)}}',\n" +
                "    eyeColor: '{{random(\"blue\", \"brown\", \"green\")}}',\n" +
                "    name: '{{firstName()}} {{surname()}}',\n" +
                "    gender: '{{gender()}}',\n" +
                "    company: '{{company().toUpperCase()}}',\n" +
                "    email: '{{email()}}',\n" +
                "    phone: '+1 {{phone()}}',\n" +
                "    address: '{{integer(100, 999)}} {{street()}}, {{city()}}, {{state()}}, {{integer(100, 10000)}}',\n" +
                "    about: '{{lorem(1, \"paragraphs\")}}',\n" +
                "    registered: '{{date(new Date(2014, 0, 1), new Date(), \"YYYY-MM-ddThh:mm:ss Z\")}}',\n" +
                "    latitude: '{{floating(-90.000001, 90)}}',\n" +
                "    longitude: '{{floating(-180.000001, 180)}}',\n" +
                "    tags: [\n" +
                "      '{{repeat(7)}}',\n" +
                "      '{{lorem(1, \"words\")}}'\n" +
                "    ],\n" +
                "    friends: [\n" +
                "      '{{repeat(3)}}',\n" +
                "      {\n" +
                "        id: '{{index()}}',\n" +
                "        name: '{{firstName()}} {{surname()}}'\n" +
                "      }\n" +
                "    ],\n" +
                "    greeting: function (tags) {\n" +
                "      return 'Hello, ' + this.name + '! You have ' + tags.integer(1, 10) + ' unread messages.';\n" +
                "    },\n" +
                "    favoriteFruit: function (tags) {\n" +
                "      var fruits = ['apple', 'banana', 'strawberry'];\n" +
                "      return fruits[tags.integer(0, fruits.length - 1)];\n" +
                "    }\n" +
                "  }\n" +
                "]";
        Assert.assertNull(JsonParser.parseJson(brokenJson));
    }
    @Test
    public void insertCorrectJson(){
        String correctJson = "[" + "{\n" +
                "  \"glew:x86-windows\": {\n" +
                "    \"package_name\": \"glew\",\n" +
                "    \"triplet\": \"x86-windows\",\n" +
                "    \"version\": \"2.1.0\",\n" +
                "    \"port_version\": 10,\n" +
                "    \"features\": [],\n" +
                "    \"desc\": [\n" +
                "      \"The OpenGL Extension Wrangler Library (GLEW) is a cross-platform open-source C/C++ extension loading library.\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"opengl:x86-windows\": {\n" +
                "    \"package_name\": \"opengl\",\n" +
                "    \"triplet\": \"x86-windows\",\n" +
                "    \"version\": \"0.0\",\n" +
                "    \"port_version\": 8,\n" +
                "    \"features\": [],\n" +
                "    \"desc\": [\n" +
                "      \"Open Graphics Library (OpenGL)[3][4][5] is a cross-language, cross-platform application programming interface (API) for rendering 2D and 3D vector graphics.\"\n" +
                "    ]\n" +
                "  }\n" +
                "}"
                + "]";
        JSONArray jsonArray = new JSONArray("[{\"triplet\":\"x86-windows\",\"features\":[],\"port_version\":8,\"package_name\":\"opengl\",\"name\":\"opengl:x86-windows\",\"version\":\"0.0\",\"desc\":[\"Open Graphics Library (OpenGL)[3][4][5] is a cross-language, cross-platform application programming interface (API) for rendering 2D and 3D vector graphics.\"]},{\"triplet\":\"x86-windows\",\"features\":[],\"port_version\":10,\"package_name\":\"glew\",\"name\":\"glew:x86-windows\",\"version\":\"2.1.0\",\"desc\":[\"The OpenGL Extension Wrangler Library (GLEW) is a cross-platform open-source C/C++ extension loading library.\"]}]");
        Assert.assertEquals(jsonArray.toString(), JsonParser.parseJson(correctJson).toString());
    }
}
