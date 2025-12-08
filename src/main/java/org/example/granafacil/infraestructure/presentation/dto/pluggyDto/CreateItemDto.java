package org.example.granafacil.infraestructure.presentation.dto.pluggyDto;

import lombok.ToString;

@ToString
public class CreateItemDto {
    private Item item;



    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public static class Item {
        private String id;
        private String status;
        private String lastUpdatedAt;
        private String createdAt;
        private Connector connector;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLastUpdatedAt() {
            return lastUpdatedAt;
        }

        public void setLastUpdatedAt(String lastUpdatedAt) {
            this.lastUpdatedAt = lastUpdatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Connector getConnector() {
            return connector;
        }

        public void setConnector(Connector connector) {
            this.connector = connector;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", status='" + status + '\'' +
                    ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", connector=" + connector +
                    '}';
        }
    }


    public static class Connector {
        private Long id;
        private String name;
        private String imageUrl;
        private Health health;
        private String type;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Health getHealth() {
            return health;
        }

        public void setHealth(Health health) {
            this.health = health;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Connector{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", health=" + health +
                    ", type='" + type + '\'' +
                    '}';
        }
    }


    public static class Health {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Health{" +
                    "status='" + status + '\'' +
                    '}';
        }
    }




}
