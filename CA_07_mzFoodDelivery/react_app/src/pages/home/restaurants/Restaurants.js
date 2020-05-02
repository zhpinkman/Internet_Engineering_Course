import React from "react";
import "./restaurants.css";
import TextTitleCenter from "../../general/text/TextTitleCenter";
import RestaurantCard from "./RestaurantCard";
import RestaurantService from "../../../services/RestaurantService";
import {homeRestaurantsRefresh} from "../../../services/subjects/MessageService";

export default class Restaurants extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurants: null,
            page: 1,
            loadingMore: false,
            enableLoadMore: true,
            serviceFunction: RestaurantService.getRestaurants,
            searchPhrase: null,
            noContent: false
        };
    }

    componentDidMount() {
        this.getRestaurants();
        document.addEventListener('scroll', this.trackScrolling);

        homeRestaurantsRefresh.asObservable().subscribe((argsList) => {
            if (!this.state.loadingMore) {
                let serviceFunction = argsList[0];
                let searchPhrase = argsList[1];
                console.log(argsList);
                this.setState({
                        serviceFunction: serviceFunction,
                        searchPhrase: searchPhrase,
                        page: 1,
                        restaurants: null,
                        enableLoadMore: true,
                        noContent: false
                    },
                    function () {
                        this.getRestaurants();
                    }
                );
            }
        });

    }

    componentWillUnmount() {
        document.removeEventListener('scroll', this.trackScrolling);
    }

    async getRestaurants() {
        this.setState({loadingMore: true});
        let restaurants;
        if (this.state.searchPhrase === "" || this.state.searchPhrase === null) {
            restaurants = await this.state.serviceFunction(this.state.page);
        } else {
            restaurants = await this.state.serviceFunction(this.state.searchPhrase, this.state.page);
        }
        if (!restaurants.length > 0) {
            this.setState({enableLoadMore: false});
            if (this.state.restaurants == null || this.state.restaurants.length === 0) {
                this.setState({noContent: true});
            }
        }
        if (this.state.restaurants != null) {
            restaurants = this.state.restaurants.concat(restaurants);
        }
        console.log(restaurants);
        this.setState(
            {
                restaurants: restaurants,
                loadingMore: false
            }
        )
    }

    gotoNextPage() {
        if (this.state.loadingMore === false) {
            this.setState({
                    loadingMore: true,
                    page: this.state.page + 1
                }, function () {
                    this.getRestaurants();
                }
            );
        }
    }

    isBottom(el) {
        return el.getBoundingClientRect().bottom <= window.innerHeight;
    }

    trackScrolling = () => {
        const wrappedElement = document.getElementById('restaurants-container');
        if (this.isBottom(wrappedElement) && this.state.enableLoadMore) {
            this.gotoNextPage();
            // console.log('header bottom reached');
            // document.removeEventListener('scroll', this.trackScrolling);
        }
        if (!this.state.enableLoadMore) {
            this.setState({loadingMore: false});
        }
    };

    render() {
        return (
            <div className="restaurants-container mt-5" id={"restaurants-container"}>
                <TextTitleCenter text="رستوران ها"/>

                <div className="row justify-content-center mt-3">
                    {this.restaurantsList()}
                </div>
                {this.state.loadingMore && <div className="progress-line"/>}
            </div>
        );
    }

    restaurantsList() {
        if (this.state.restaurants == null) {
            // return (
            //     <div className="spinner-grow text-danger food-party-loading-box align-self-center" role="status">
            //         <span className="sr-only">Loading...</span>
            //     </div>
            // );
        } else if (this.state.noContent) {
            return (
                <div className="align-self-center">
                    <img className="align-self-center no-content-img"
                         src={require("../../../Assets/images/no_content_illustration_v2.svg")} alt={""}/>
                    <div className="text-center">
                        چیزی پیدا نشد!
                    </div>
                </div>
            );
        } else {
            return this.state.restaurants.map((restaurant, i) =>
                <RestaurantCard restaurant={restaurant} key={"R" + i}/>
            );
        }
    }
}

