import logging_config
import data_manager
import logging
from datetime import datetime, timezone

if __name__ == "__main__":
    # data_manager.add_user("John Smith", "123456798abc", datetime.now(timezone.utc))
    # data_manager.change_user_password("Peter Duan", "12345")
    # data_manager.delete_user("John Smith")

    """data_manager.add_user("John Smith", "123456798abc", datetime.now(timezone.utc))
    data_manager.add_user("Peter Duan", "3344kkas", datetime.now(timezone.utc))
    data_manager.add_user("Xinglin Wang", "asdfg", datetime.now(timezone.utc))
    data_manager.add_user("Yeegee Giao", "ggxx123", datetime.now(timezone.utc))
    data_manager.add_user("Vincent Huang", "5r5sszzz", datetime.now(timezone.utc))
    """
    
    data_manager.delete_user("Xinglin Wang")