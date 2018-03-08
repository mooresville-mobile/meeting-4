//
//  ViewController.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/7/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController {
    let cellIdentifier = "MovieCell"
    let searchManager = SearchNetworkManager()
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    var movies: [Movie] = [] {
        didSet {
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let activity = UIActivityIndicatorView(activityIndicatorStyle: .gray)
        activity.hidesWhenStopped = true
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: activity)
        activityIndicator = activity
    }
    
    @IBAction func runSearch(_ sender: UIButton) {
        activityIndicator.startAnimating()
        searchManager.performSearch(with: "terminator") { result in
            defer {
                DispatchQueue.main.async {
                    self.activityIndicator.stopAnimating()
                }
            }
            
            switch result {
            case .success(let movies):
                guard let movieResults = movies else {
                    print("No movies returned")
                    return
                }
                
                self.movies = movieResults
            case .error(let error):
                print("Error encountered: \(error)")
            }
        }
    }
}

extension SearchViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let movieCell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier,
                                                            for: indexPath) as? MovieTableViewCell else {
                                                                fatalError("Unable to retrieve correct cell")
        }
        
        let movie = movies[indexPath.row]
        movieCell.configure(with: movie)
        return movieCell
    }
}

