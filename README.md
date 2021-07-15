# ezipay-subscription-app
ezipay-admin-app - will be running on 8081 \
ezipay-client-app - will be running on 8080

##Some of the endpoint:- \
Create New Customer with Subscription \
>[Method POST] 
>[End Point] http://localhost:8081/api/customer \
>[JSON body] \

```
{
    "firstName": "iris3",
    "lastName": "fahmiasdasdasd",
    "email": "iris2@ggg.com",
    "password":"12345",
    "subscription": "MONTHLY",
    "price": 100.0,
    "subs_start_date": 1625068800000,
    "subs_end_date": 1632931200000
}
```
Response will be:-

```
{
    "id": 2,
    "firstName": "iris3",
    "lastName": "fahmiasdasdasd",
    "email": "iris2@ggg.com",
    "password": "12345",
    "subscription": "MONTHLY",
    "price": 100.0,
    "subs_start_date": 1625068800000,
    "subs_end_date": 1632931200000,
    "str_subs_start_date": null,
    "str_subs_end_date": null,
    "invoices": [
        {
            "invoiceDate": 1625068800000
        },
        {
            "invoiceDate": 1627747200000
        },
        {
            "invoiceDate": 1630425600000
        }
    ]
}
```
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/SaveCustomer.png)

>Update Data: Make sure id is exist\
>[Method POST] 
>[End Point] http://localhost:8081/api/customer \
>[JSON body] \
```
{ 
    "id": 2,
    "firstName": "iris3",
    "lastName": "fahmi",
    "email": "iris@ggg.com",
    "password":"12345",
    "subscription": "MONTHLY",
    "price": 100.0,
    "subs_start_date": 1625068800000,
    "subs_end_date": 1632931200000
}
```
The result will be: 
```
{
    "id": 2,
    "firstName": "iris3",
    "lastName": "fahmi",
    "email": "iris@ggg.com",
    "password": "12345",
    "subscription": "MONTHLY",
    "price": 100.0,
    "subs_start_date": 1625068800000,
    "subs_end_date": 1632931200000,
    "str_subs_start_date": null,
    "str_subs_end_date": null,
    "invoices": [
        {
            "invoiceDate": 1625068800000
        },
        {
            "invoiceDate": 1627747200000
        },
        {
            "invoiceDate": 1630425600000
        }
    ]
}
```
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/UpdateCustomer.png)



# ezipay-client-app screenshots

>URL : http://localhost:8080

>Login Page \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/client1.png)

>Register Page \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/client2register.png)

>Customer Login \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/client3login.png)

>Main Screen / User and Subscription Information  \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/client4afterLogin.png)


>Update User and Subscription Information  \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/client5update.png)


# ezipay-admin-app screenshots
>URL - http://localhost:8081
>Landing Page \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/admin1.png)

>Login Page \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/admin2.png)

>Admin Landing Page (after login) \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/admin3.png)

>Customer Listing Page \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/admin4.png)

>Customer detail and subscription details (read only) \
Screenshot: \
![alt text](https://github.com/shazone/ezipay-subscription-app/blob/main/admin5.png)


