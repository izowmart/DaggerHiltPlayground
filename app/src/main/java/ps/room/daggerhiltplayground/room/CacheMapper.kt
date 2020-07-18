package ps.room.daggerhiltplayground.room

import ps.room.daggerhiltplayground.model.Blog
import ps.room.daggerhiltplayground.retrofit.BlogNetworkEntity
import ps.room.daggerhiltplayground.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            entity.id,
            entity.title,
            entity.body,
            entity.image,
            entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.category
        )
    }
    fun mapFromEntityList(entities:List<BlogCacheEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }

}