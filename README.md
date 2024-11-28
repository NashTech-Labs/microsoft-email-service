## Steps to Create Client ID, Client Secret, and Tenant ID


1. **Log into the Azure Portal:**
    - Go to Azure Portal(https://portal.azure.com/).
    - Sign in with your Microsoft account.   

2. **Create a New Application Registration:**
   - Navigate to Azure Active Directory in the left sidebar.
   - Select App registrations > New registration.
   - Enter a name for your app (e.g., MyApp), and select the appropriate Supported account types.
   - Click Register.

3. **Get Tenant ID:** 
   - After registration, go to your app's page.    
   - Under Overview, find your Tenant ID (also called Directory ID). Copy it for later use.
4. **Get Client ID (Application ID):**
     - Under Overview, you'll also find your Client ID (Application ID). Copy it for later use.
5. **Create Client Secret:**
     - In your app registration, go to Certificates & secrets.
     - Under the Client secrets section, click New client secret.
     - Add a description and select an expiration period.
     - Click Add.
     - Copy the Value of the client secret immediately after creation (you won’t be able to see it again).
6. **Grant API Permissions (Application Permission):**
   - Go to API permissions > Add a permission.
   - Choose Microsoft Graph or another API.
   - Select the required permissions (e.g., Mail.Send for email).
7. **Configure Redirect URI (if needed):**
   - Under Authentication, you can add a Redirect URI if your app needs one (for OAuth flows).


## Use Tenant ID, Client ID, and Client Secret value in env(.env-email-cred) along with sender and receiver mail.

Create a env file with name **.env-email-cred**   

## To run Application
```shell
sbt compile
```

```shell
sbt run
```