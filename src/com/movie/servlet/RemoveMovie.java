package com.movie.servlet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/RemoveMovie")
public class RemoveMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray jarray = new JSONArray();

	public RemoveMovie() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject movie = new JSONObject();

		// setting response type
		response.setContentType("application/json");
		String moviename = request.getParameter("moviename");
		PrintWriter out = response.getWriter();
		out.print("Thank you for removing <b>" + moviename + "</b> to your favorite locations");
		// shows the city added
		String fileName = "/home/akash/Work/workspace-sts-3.9.2.RELEASE/MovieMagic/favorit.json";
		JSONParser parser = new JSONParser();
		try {
			array = (JSONArray) parser.parse(new FileReader(fileName));
			// if id exists, do not add and return error
			for (int looper = 0; looper < array.size(); looper++) {
				movie = (JSONObject) array.get(looper);
				if (String.valueOf(movie.get("moviename")).equals(String.valueOf(moviename))) {
					array.remove(looper);
					FileWriter jsonFile = null;
					try {
						jsonFile = new FileWriter(
								"/home/akash/Work/workspace-sts-3.9.2.RELEASE/MovieMagic/favorit.json");
						jsonFile.write(array.toString());
						// System.out.println(json.toString());
					} catch (Exception e) {
						System.out.println("Please enter a valid path where you want to store your json");
					} finally {
						jsonFile.flush();
						jsonFile.close();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}