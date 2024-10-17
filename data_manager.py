from datetime import datetime
import os
import csv
import logging
import json
from pathlib import Path
import shutil


DATA_DIR_PATH = f"{os.path.dirname(os.path.abspath(__file__))}/data"
USER_FILE_PATH = f"{DATA_DIR_PATH}/users.json"
LOGGER = logging.getLogger("my_logger")


class UserError(Exception):
    pass


class UserAlreadyExistsError(UserError):
    def __init__(self, username):
        super().__init__(f"User '{username}' already exists.")
        self.username = username


class UserNotFoundError(UserError):
    def __init__(self, username):
        super().__init__(f"User '{username}' not found.")
        self.username = username


class UserSamePasswordError(UserError):
    def __init__(self, username, password):
        super().__init__(f"New password '{password}' for user '{username}' is same to the old password.")
        self.username = username
        self.password = password


def _get_data_file_path(username: str, datetime: datetime) -> str:
    return f"{DATA_DIR_PATH}/{username}/{datetime.strftime("%b-%d-%Y")}.csv"


def _get_data_dir_path(username: str) -> str:
    return f"{DATA_DIR_PATH}/{username}"


def add_user(username: str, password: str, join_datetime_utc: datetime) -> None:
    if not os.path.exists(USER_FILE_PATH):
        Path(USER_FILE_PATH).parent.mkdir(parents=True, exist_ok=True)
        with open(USER_FILE_PATH, 'w') as file:
            json.dump([], file)
    
    with open(USER_FILE_PATH, 'r') as file:
        users = json.load(file)
    
    if username in [user["username"] for user in users]:
        LOGGER.warning(f"Failed to add user '{username}' since this user already exists.")
        raise UserAlreadyExistsError(username)
    
    users.append({
        "username": username,
        "password": password,
        "join_datetime_utc": join_datetime_utc.strftime("%b-%d-%Y %H:%M:%S")
    })

    with open(USER_FILE_PATH, 'w') as file:
        json.dump(users, file, indent=4)

    LOGGER.info(f"Added user '{username}'.")


def delete_user(username: str) -> None:
    if not os.path.exists(USER_FILE_PATH):
        LOGGER.warning(f"Failed to delete user '{username}' since user data file '{USER_FILE_PATH}' does not exist.")
        raise UserNotFoundError(username)
    
    with open(USER_FILE_PATH, 'r') as file:
        users = json.load(file)

    if username not in [user["username"] for user in users]:
        LOGGER.warning(f"Failed to delete user '{username}' since this user does not exist.")
        raise UserNotFoundError(username)
    
    for user in users:
        if user["username"] == username:
            users.remove(user)
            break
    
    with open(USER_FILE_PATH, 'w') as file:
        json.dump(users, file, indent=4)
    
    if os.path.exists(_get_data_dir_path(username)):
        shutil.rmtree(_get_data_dir_path(username))
        LOGGER.info(f"Deleted user '{username}' and related data in dir '{_get_data_dir_path(username)}'")
    else:
        LOGGER.info(f"Deleted user '{username}'. This user has no related data.")


def change_user_password(username: str, new_password: str) -> None:
    if not os.path.exists(USER_FILE_PATH):
        LOGGER.warning(f"Failed to change password for user '{username}' since user data file '{USER_FILE_PATH}' does not exist.")
        raise UserNotFoundError(username)
    
    with open(USER_FILE_PATH, 'r') as file:
        users = json.load(file)

    if username not in [user["username"] for user in users]:
        LOGGER.warning(f"Failed to change password for user '{username}' since this user does not exist.")
        raise UserNotFoundError(username)
    
    for user in users:
        if user["username"] == username:
            if user["password"] == new_password:
                LOGGER.warning(f"Failed to change password for user '{username}' because new password '{new_password}' is same to the old one.")
                raise UserSamePasswordError(username, new_password)
            else:
                old_password = user["password"]
                user["password"] = new_password
    
    with open(USER_FILE_PATH, 'w') as file:
        json.dump(users, file, indent=4)
    
    LOGGER.info(f"Changed password of user '{username}' from '{old_password}' to '{new_password}'.")


def add_record(username: str, food: str, energy_kj: float, datetime: datetime) -> None:
    pass


def delete_record(username: str, datetime: datetime) -> None:
    pass