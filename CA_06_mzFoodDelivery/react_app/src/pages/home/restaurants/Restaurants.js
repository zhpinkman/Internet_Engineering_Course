import React from "react";
import "./restaurants.css";
import TextTitleCenter from "../../general/text/TextTitleCenter";
import RestaurantCard from "./RestaurantCard";
import RestaurantService from "../../../services/RestaurantService";

export default class Restaurants extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurants: null,
            page: 1,
            loadingMore: false
        };
    }

    componentDidMount() {
        this.getRestaurants();
        document.addEventListener('scroll', this.trackScrolling);
    }

    componentWillUnmount() {
        document.removeEventListener('scroll', this.trackScrolling);
    }

    async getRestaurants() {
        this.setState({loadingMore: true})
        let restaurants = await RestaurantService.getRestaurants(this.state.page);
        if(this.state.restaurants != null){
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
        if(this.state.loadingMore === false){
            this.setState({
                loadingMore: true,
                page: this.state.page + 1
            });
            this.getRestaurants();
        }
    }

    isBottom(el) {
        return el.getBoundingClientRect().bottom <= window.innerHeight;
    }

    trackScrolling = () => {
        const wrappedElement = document.getElementById('restaurants-container');
        if (this.isBottom(wrappedElement)) {
            this.gotoNextPage();
            console.log('header bottom reached');
            // document.removeEventListener('scroll', this.trackScrolling);
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
        } else {
            return this.state.restaurants.map((restaurant, i) =>
                <RestaurantCard restaurant={restaurant} key={"R" + i}/>
            );
        }
    }
}

