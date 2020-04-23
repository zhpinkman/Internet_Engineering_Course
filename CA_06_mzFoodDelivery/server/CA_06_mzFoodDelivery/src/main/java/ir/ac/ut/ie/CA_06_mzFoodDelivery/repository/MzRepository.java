package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.OrderItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Delivery.DeliveryMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food.FoodMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Order.OrderMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.OrderItem.OrderItemMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.User.UserMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.UserCart.UserCartMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MzRepository {
    private static MzRepository instance;

    private MzRepository() {
    }

    public static MzRepository getInstance() {
        if (instance == null)
            instance = new MzRepository();
        return instance;
    }

    public void createAllTables() {
        try {
            OrderMapper orderMapper = new OrderMapper(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            OrderItemMapper orderItemMapper = new OrderItemMapper(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DeliveryMapper deliveryMapper = new DeliveryMapper(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UserCartMapper userCartMapper = new UserCartMapper(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FoodMapper foodMapper = new FoodMapper(true);
        } catch (Exception ignored) {
        }
        try {
            RestaurantMapper restaurantMapper = new RestaurantMapper(true);
        } catch (Exception ignored) {
        }
        try {
            UserMapper userMapper = new UserMapper(true);
        } catch (Exception ignored) {
        }
    }

    //    RESTAURANT
    public void insertRestaurant(Restaurant restaurant) throws SQLException {
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        restaurantMapper.insert(restaurant);
    }

    public Restaurant findRestaurantById(String restaurantId) throws SQLException {
        return new RestaurantMapper().find(restaurantId);
    }

    public Restaurant findRestaurantById(String restaurantId, boolean includeMenu) throws SQLException {
        if (includeMenu) {
            Restaurant restaurant = new RestaurantMapper().find(restaurantId);
            List<Food> menu = MzRepository.getInstance().getRestaurantMenu(restaurantId);
            restaurant.setMenu(menu);
            return restaurant;
        } else {
            return findRestaurantById(restaurantId);
        }
    }


    //    FOOD
    public void insertFood(Food food) throws SQLException {
        FoodMapper foodMapper = new FoodMapper();
        foodMapper.insert(food);
    }

    public void deleteFood(CustomPair id) throws SQLException {
        new FoodMapper().delete(id);
    }

    public Food getFood(String restaurantId, String foodName) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(restaurantId);
        args.add(foodName);
        return new FoodMapper().find(new CustomPair(args));
    }

    private List<Food> getRestaurantMenu(String restaurantId) throws SQLException {
        return new FoodMapper().getRestaurantMenu(restaurantId);
    }

    //    FOOD PARTY
    public void deletePartyFoods() throws SQLException {
        FoodMapper foodMapper = new FoodMapper();
        foodMapper.deletePartyFoods();
    }

    public List<PartyFood> getPartyFoods() throws SQLException {
        return new FoodMapper().getPartyFoods();
    }

    //    USER
    public void insertUser(User user) throws SQLException {
        new UserMapper().insert(user);
    }

    public User getUser(String email) throws SQLException {
        return new UserMapper().find(email);
    }

    public List<Restaurant> findNearRestaurants(Location location, double maxDistance, int limit, int offset) throws SQLException {
        return new RestaurantMapper().findNearRestaurants(location, maxDistance, limit, offset);
    }

    public void updateUser(User user) throws SQLException {
        new UserMapper().updateUserCredit(user);
    }

    //      USERCART
    public void insertCartItem(CartItem cartItem) throws SQLException {
        new UserCartMapper().insert(cartItem);
    }

    public void emptyUserCart(String userEmail) throws SQLException {
        new UserCartMapper().emptyUserCart(userEmail);
    }

    public List<CartItem> getUserCart(String userEmail) throws SQLException {
        return new UserCartMapper().getUserCart(userEmail);
    }

    public void updateCartItem(CartItem cartItem) throws SQLException {
        new UserCartMapper().updateCartItem(cartItem);
    }

    public void removeCartItem(CartItem cartItem) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(cartItem.getUserEmail());
        args.add(cartItem.getRestaurantId());
        args.add(cartItem.getFoodName());
        new UserCartMapper().delete(new CustomPair(args));
    }


    public CartItem findCartItem(CartItem cartItem) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(cartItem.getUserEmail());
        args.add(cartItem.getRestaurantId());
        args.add(cartItem.getFoodName());
        return new UserCartMapper().find(new CustomPair(args));
    }


    public List<Restaurant> searchRestaurants(String searchPhrase, int limit, int offset) {
        try {
            return new RestaurantMapper().search(searchPhrase, limit, offset);
        } catch (SQLException e) {
            return new ArrayList<Restaurant>();
        }
    }

    public List<Restaurant> searchFoods(String searchPhrase, int limit, int offset) {
        try {
            List<Food> foods = new FoodMapper().searchUniqueRestaurants(searchPhrase, limit, offset);
            List<Restaurant> resultRestaurants = new ArrayList<>();
            List<String> restaurantIds = new ArrayList<>();
            for (Food food : foods) {
                try {
                    Restaurant newRestaurant = MzRepository.getInstance().findRestaurantById(food.getRestaurantId());
                    if (!restaurantIds.contains(newRestaurant.getId())) {
                        restaurantIds.add(newRestaurant.getId());
                        resultRestaurants.add(newRestaurant);
                    }
                } catch (SQLException ignored) {
                }
            }
            return resultRestaurants;
        } catch (SQLException e) {
            return new ArrayList<Restaurant>();
        }
    }


    public void addOrderItem(OrderItem orderItem) throws SQLException {
        new OrderItemMapper().insert(orderItem);
    }

    public void addUserOrder(Order userOrder) throws SQLException {
        new OrderMapper().insert(userOrder);
    }

    public Order getOrder(String userEmail, int orderId) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(userEmail);
        args.add(String.valueOf(orderId));
        return new OrderMapper().find(new CustomPair(args));
    }


    public List<OrderItem> getOrderItems(String userEmail, int orderId) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(userEmail);
        args.add(String.valueOf(orderId));
        return new OrderItemMapper().getOrderItems(new CustomPair(args));
    }

    public void updateOrder(Order order) throws SQLException {
        new OrderMapper().updateOrder(order);
    }

    public void updatePartyFood(PartyFood partyFood) throws SQLException {
        new FoodMapper().updateFood(partyFood);
    }


//    deliveries
    public void addDelivery(Delivery delivery) throws  SQLException{
        new DeliveryMapper().insert(delivery);
    }

    public int getDeliveriesCount() throws SQLException{
        return new DeliveryMapper().getDeliveriesCount();
    }

    public void removeDelivery(String id) throws SQLException {
        new DeliveryMapper().delete(id);
    }

    public void removeAllDeliveries() throws SQLException {
        new DeliveryMapper().removeAllDeliveries();
    }


    public List<Delivery> getDeliveries() throws SQLException {
        return new DeliveryMapper().getAllDeliveries();
    }

    public List<Order> getOrders(String userEmail) throws SQLException {
        return new OrderMapper().getOrders(userEmail);
    }
}
