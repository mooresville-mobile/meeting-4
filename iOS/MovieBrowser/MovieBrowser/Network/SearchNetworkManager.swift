//
//  SearchNetworkManager.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/7/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import Foundation

typealias MovieSearchCompletion = (ResultType<[Movie]?>) -> Void

enum NetworkError: Error {
    case badURL
    case searchFailed(String)
    case undetermined
}

struct SearchNetworkManager {
    let baseUrlString = "https://api.themoviedb.org"
    
    var baseQueryParams: [URLQueryItem] = [
            URLQueryItem(name: "api_key", value: AppConstants.apiKey),
            URLQueryItem(name: "language", value: "en-us")
        ]
    
    
    func performSearch(with searchTerm: String, completion: @escaping MovieSearchCompletion) {
        let params = [
            URLQueryItem(name: "query", value: searchTerm)
        ]
        
        guard let url = constructURL(action: ApiAction.search,
                                     topic: ApiModelType.movie,
                                     params: params,
                                     apiVersion: "3") else {
                                        completion(.error(NetworkError.badURL))
                                        return
        }
        
        let defaultSession = URLSession(configuration: .default)
        let dataTask = defaultSession.dataTask(with: url) { (data, response, error) in
            guard let response = response as? HTTPURLResponse,
                let data = data,
                response.statusCode >= 200,
                response.statusCode < 300 else {
                    let networkError = NetworkError.undetermined
                    completion(.error(networkError))
                    return
            }

            if let error = error {
                let failureReason = error.localizedDescription
                let networkError = NetworkError.searchFailed(failureReason)
                completion(.error(networkError))
            }
            
            let movieList = MovieList.from(jsonListData: data)
            if let movieList = movieList {
                completion(.success(movieList.movies))
            }
        }
        
        dataTask.resume()
    }
    
    private func constructURL(action: ApiAction, topic: ApiModelType, params: [URLQueryItem], apiVersion: String) -> URL? {
        // https://api.themoviedb.org/3/search/movie?api_key=99651ffbdb70f6a2d891f47ea9928677&language=en-US&query=terminator&include_adult=false

        guard let url = URL(string: baseUrlString) else {
            return nil
        }

        var queryParams = baseQueryParams
        queryParams.append(contentsOf: params)
        
        var urlComponents = URLComponents(url: url,
                                          resolvingAgainstBaseURL: false)
        urlComponents?.path = "/\(apiVersion)/\(action.rawValue)/\(topic.rawValue)"
        urlComponents?.queryItems = queryParams
        let finalUrl = urlComponents?.url
        
        return finalUrl
    }
}
