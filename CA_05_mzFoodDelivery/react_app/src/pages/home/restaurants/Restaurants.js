import React from "react";
import TextTitleCenter from "../../general/text/TextTitleCenter";
import RestaurantCard from "./RestaurantCard";
import RestaurantService from "../../../services/RestaurantService";

export default class Restaurants extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurants: null
        };
    }

    componentDidMount() {
        this.getRestaurants();
    }

    async getRestaurants() {
        let restaurants = await RestaurantService.getRestaurants();
        console.log(restaurants);
        this.setState(
            {
                restaurants: restaurants
            }
        )
    }

    render() {
        return (
            <div className="restaurants-container mt-5">
                <TextTitleCenter text="رستوران ها"/>

                <div className="row justify-content-center mt-3">
                    {this.restaurantsList()}
                </div>

            </div>
        );
    }

    restaurantsList() {
        if (this.state.restaurants == null) {
            return (
                <div className="spinner-grow text-danger food-party-loading-box align-self-center" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            );
        } else {
            return this.state.restaurants.slice(0, 8).map((restaurant, i) =>
                <RestaurantCard restaurant={restaurant} home={this.props.home} key={"R"+i}/>
            );
        }
    }

}